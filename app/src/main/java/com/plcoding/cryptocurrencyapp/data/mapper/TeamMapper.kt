package com.plcoding.cryptocurrencyapp.data.mapper

import com.plcoding.cryptocurrencyapp.data.local.TeamMemberEntity
import com.plcoding.cryptocurrencyapp.data.remote.dto.TeamMember

fun TeamMemberEntity.toTeamMember(): TeamMember {
    return TeamMember(
        id = member_id,
        name = name,
        position = position
    )
}

fun TeamMember.toTeamMemberEntity(): TeamMemberEntity {
    return TeamMemberEntity(
        member_id = id,
        name = name,
        position = position
    )
}