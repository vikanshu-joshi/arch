package com.example.practice.detail.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.practice.detail.composable.ChainGroupSectionUI
import com.example.practice.detail.composable.LocationSectionUI
import com.example.practice.detail.composable.ReviewRatingsSectionUI

data class HTLDetailInfoCardUiState(
    val tags: List<String> = listOf(),
    val stars: Int = 0,
    val hotelName: String = "",
    val sections: List<HotelInfoCardSection> = listOf(),
    val description: String = ""
)

sealed class HotelInfoCardSection() {

    data class ReviewRatingsSection(
        val starRating: Double,
        val title: String,
        val accentColor: Color,
        val subtitle: String,
        val trailingIcon: ImageVector,
        val onTrailingIconClicked: () -> Unit = {}
    ): HotelInfoCardSection() {
        @Composable
        override fun UI(modifier: Modifier) {
            ReviewRatingsSectionUI(section = this, modifier = modifier)
        }
    }

    data class LocationSection(
        val iconUrl: ImageVector,
        val title: String,
        val trailingIcon: ImageVector?,
        val onTrailingIconClicked: () -> Unit = {}
    ): HotelInfoCardSection() {
        @Composable
        override fun UI(modifier: Modifier) {
            LocationSectionUI(section = this, modifier = modifier)
        }
    }

    data class ChainGroupSection(
        val iconUrl: ImageVector,
        val title: String,
        val trailingIcon: ImageVector?,
        val onTrailingIconClicked: () -> Unit = {}
    ): HotelInfoCardSection() {
        @Composable
        override fun UI(modifier: Modifier) {
            ChainGroupSectionUI(section = this, modifier = modifier)
        }
    }

    @Composable
    abstract fun UI(modifier: Modifier)

}