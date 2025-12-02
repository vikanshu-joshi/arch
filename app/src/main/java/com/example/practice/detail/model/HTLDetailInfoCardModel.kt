package com.example.practice.detail.model

/**
 * This class will contain all the data that a card requires to function.
 * this will be raw data derived from api response
 * each cards VM will later on process this raw response and transform into UI accordingly
 * */
data class HTLDetailInfoCardModel(
    val tags: List<String>,
    val hotelName: String,
    val rating: Double,
    val ratingsType: String,
    val totalRatings: Int,
    val ratingsInfoText: String,
    val locationIcon: String,
    val locationName: String,
    val locationInfo: String,
    val chainName: String,
    val chainIcon: String,
    val description: String
)