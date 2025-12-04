package com.example.practice.detail.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.practice.detail.base.DetailTracker
import com.example.practice.detail.callbacks.HTLDetailInfoCardCallbacks
import com.example.practice.detail.model.HTLDetailInfoCardModel
import com.example.practice.detail.state.HTLDetailInfoCardUiState
import com.example.practice.detail.state.HotelInfoCardSection
import com.example.practice.detail.tracker.HTLDetailInfoTracker
import kotlin.math.roundToInt


class HTLDetailInfoCardVM(private val data: HTLDetailInfoCardModel, private val callbacks: HTLDetailInfoCardCallbacks, private val tracker: HTLDetailInfoTracker) {

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
            onTrailingIconClicked = ::onReviewRatingsClicked
        )
    }

    private fun getLocationSection(): HotelInfoCardSection.LocationSection? {
        return HotelInfoCardSection.LocationSection(
            iconUrl = Icons.Default.LocationOn,
            title = data.locationName + " | " + data.locationInfo,
            trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onTrailingIconClicked = ::onLocationClicked
        )
    }

    private fun getChainGroupSection(): HotelInfoCardSection.ChainGroupSection? {
        return HotelInfoCardSection.ChainGroupSection(
            iconUrl = Icons.Default.Home,
            title = data.locationName + " | " + data.locationInfo,
            trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onTrailingIconClicked = ::onChainGroupClicked
        )
    }


    private fun onReviewRatingsClicked() {
        tracker.onReviewRatingsClicked()
        callbacks.onReviewRatingsClicked()
    }

    private fun onLocationClicked() {
        tracker.onLocationClicked()
        callbacks.onLocationClicked()
    }

    private fun onChainGroupClicked() {
        tracker.chainGroupClicked()
        callbacks.onChainGroupClicked()
    }

    private fun onCardSeen() {
        tracker.onCardSeen()
    }

}