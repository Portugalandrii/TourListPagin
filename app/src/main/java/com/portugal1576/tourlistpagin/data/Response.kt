package com.portugal1576.tourlistpagin.data

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("articles")
    val code: Int,
    val entities: List<Entity>,
    val loadMore: Boolean
)

data class Entity(

    @SerializedName("urlToImage")
    val age: Int,
    val arrow_type: Int,
    val distance: Int,
    val downloads: Int,
    val duration: Int,
    val id: Int,
    val image: String,
    val lat: Double,
    val lng: Double,
    val owner: Int,
    val rating: Int,
    val real_country: String,
    val title: String,
    val type: Int,
    val updated_at: String,
    val views: Int
)
