package com.vimalcvs.myapplication.data

import kotlinx.serialization.Serializable

@Serializable
data class ModelLive(
    val score: Score, val subtitle: String, val title: String
)

@Serializable
data class Score(
    val teamA: Int, val teamB: Int
)