package com.plcoding.cryptocurrencyapp.data.repository

import android.util.Log
import com.plcoding.cryptocurrencyapp.common.Resource
import com.plcoding.cryptocurrencyapp.data.local.CoinDatabase
import com.plcoding.cryptocurrencyapp.data.mapper.toCoin
import com.plcoding.cryptocurrencyapp.data.mapper.toCoinDetail
import com.plcoding.cryptocurrencyapp.data.mapper.toCoinDetailEntity
import com.plcoding.cryptocurrencyapp.data.mapper.toCoinEntity
import com.plcoding.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyapp.domain.model.Coin
import com.plcoding.cryptocurrencyapp.domain.model.CoinDetail
import com.plcoding.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//repository in data layer - implementation of our repository interface
//contains direct access to our data
//we inject dependency - api
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private val db: CoinDatabase
): CoinRepository {

    private val dao = db.dao

    override suspend fun getCoins(fetchFromRemote: Boolean): Flow<Resource<List<Coin>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCoins = dao.getCoins()
            emit(Resource.Success(
                data = localCoins.map { it.toCoin() }
            ))
            val shouldJustLoadFromCache = localCoins.isNotEmpty() && !fetchFromRemote
            Log.d("CoinImpl", "$localCoins")
            Log.d("CoinImpl", "${localCoins.isNotEmpty()}, ${!fetchFromRemote}, $shouldJustLoadFromCache")
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            emit(Resource.Success(emptyList())) //to show nothing while waiting for result from the api call
            val remoteCoins = try {
                api.getCoins().map { it.toCoin() }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't reach server. Check your internet connection."))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
                null
            }
            Log.d("CoinImpl", "$remoteCoins")
            //when we catch data from an api, we want to insert it into our cache
            remoteCoins?.let { coins ->
                dao.clearCoins()
                dao.insertCoins(coins.map { it.toCoinEntity() })
                emit(
                    Resource.Success(
                    data = dao.getCoins().map { it.toCoin() } //db is the single source of truth - we always catch data from there
                ))
                emit(Resource.Loading<List<Coin>>(false))
            }
        }
    }

    override suspend fun getCoinById(coinId: String, fetchFromRemote: Boolean): Flow<Resource<CoinDetail>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val localCoinDetail = dao.getCoinDetail(coinId)
            emit(Resource.Success(
                data = localCoinDetail?.toCoinDetail()
            ))
            val shouldJustLoadFromCache = localCoinDetail != null && !fetchFromRemote
            Log.d("CoinImpl", "$localCoinDetail")
            Log.d("CoinImpl", "${localCoinDetail != null}, ${!fetchFromRemote}, $shouldJustLoadFromCache")
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            val remoteCoinDetail = try {
                api.getCoinById(coinId).toCoinDetail()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't reach server. Check your internet connection."))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
                null
            }
            Log.d("CoinImpl", "$remoteCoinDetail")
            remoteCoinDetail?.let {
                dao.clearCoinDetail(coinId)
                dao.insertCoinDetail(remoteCoinDetail.toCoinDetailEntity())
                emit(
                    Resource.Success(
                    data = dao.getCoinDetail(coinId)?.toCoinDetail()
                ))
                emit(Resource.Loading<CoinDetail>(isLoading = false))
            }
        }
    }
}



