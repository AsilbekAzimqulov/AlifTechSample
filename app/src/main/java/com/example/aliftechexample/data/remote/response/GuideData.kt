package com.example.aliftechexample.data.remote.response

data class GuideResponse(
    val data: List<GuideData>
)

data class GuideData(
    val url: String,
    val startDate: String,
    val endDate: String,
    val name: String,
    val icon: String,
    val objType: String,
    val loginRequired: Boolean
)
