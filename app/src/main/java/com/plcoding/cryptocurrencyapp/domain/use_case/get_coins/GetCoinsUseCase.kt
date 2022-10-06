package com.plcoding.cryptocurrencyapp.domain.use_case.get_coins

import com.plcoding.cryptocurrencyapp.common.Resource
import com.plcoding.cryptocurrencyapp.domain.model.Coin
import com.plcoding.cryptocurrencyapp.domain.repository.CoinRepository
import com.plcoding.cryptocurrencyapp.data.repository.toCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//use cases use repositories to access our api and forward that to viewmodels
class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    //use case should only have one public function, which executes that use case
    //flow - we want to emit multiple values over period of time
    //resource: sealed class - success, error or loading
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            //emitting loading status - to display progress bar
            emit(Resource.Loading<List<Coin>>())
            //get and transform coins we get from api
            val coins = repository.getCoins().map { it.toCoin() }
            //attaching data we want to pass to viewmodel
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            //if we get response code which does not start with 2
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            //if our repository or api can't talk to remote api (no internet for example)
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
        }
    }
}