package com.example.practice.detail.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.practice.detail.callbacks.HTLDetailInfoCardCallbacks
import com.example.practice.detail.model.HTLDetailInfoCardModel
import com.example.practice.detail.state.HTLDetailInfoCardUiState
import com.example.practice.detail.state.HotelInfoCardSection
import kotlin.math.roundToInt


class HTLDetailInfoCardVM(private val data: HTLDetailInfoCardModel, private val callbacks: HTLDetailInfoCardCallbacks) {

    private val _uiState = mutableStateOf(HTLDetailInfoCardUiState())
    val uiState: State<HTLDetailInfoCardUiState> = _uiState



    init {
        createUiState()
    }


    private fun createUiState() {
        _uiState.value = HTLDetailInfoCardUiState(
            tags = data.tags,
            hotelName = data.hotelName,
            stars = data.rating.roundToInt(),
            sections = getSections(),
            description = data.description
        )
    }

    private fun getSections(): List<HotelInfoCardSection> {
        return mutableListOf<HotelInfoCardSection>().apply {
            getRatingsSection()?.let { add(it) }
            getLocationSection()?.let { add(it) }
            getChainGroupSection()?.let { add(it) }
        }
    }


    private fun getRatingsSection(): HotelInfoCardSection.ReviewRatingsSection? {
        return HotelInfoCardSection.ReviewRatingsSection(
            starRating = data.rating,
            title = data.ratingsType + " (" + data.totalRatings + " Ratings)",
            accentColor = Color.Blue,
            subtitle = data.ratingsInfoText,
            trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onTrailingIconClicked = callbacks.onReviewRatingsClicked
        )
    }

    private fun getLocationSection(): HotelInfoCardSection.LocationSection? {
        return HotelInfoCardSection.LocationSection(
            iconUrl = Icons.Default.LocationOn,
            title = data.locationName + " | " + data.locationInfo,
            trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onTrailingIconClicked = callbacks.onLocationClicked
        )
    }

    private fun getChainGroupSection(): HotelInfoCardSection.ChainGroupSection? {
        return HotelInfoCardSection.ChainGroupSection(
            iconUrl = Icons.Default.Home,
            title = data.locationName + " | " + data.locationInfo,
            trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onTrailingIconClicked = callbacks.onLocationClicked
        )
    }


}