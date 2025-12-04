package com.example.practice.detail.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.detail.callbacks.HTLDetailInfoCardCallbacks
import com.example.practice.detail.composable.StarRating
import com.example.practice.detail.model.HTLDetailInfoCardModel
import com.example.practice.detail.state.HTLDetailInfoCardUiState
import com.example.practice.detail.state.HotelInfoCardSection
import com.example.practice.detail.tracker.HTLDetailInfoTracker
import com.example.practice.detail.viewmodel.HTLDetailInfoCardVM

@Composable
fun HTLDetailInfoCard(
    modifier: Modifier = Modifier,
    data: HTLDetailInfoCardModel,
    callbacks: HTLDetailInfoCardCallbacks,
    tracker: HTLDetailInfoTracker,
) {
    val viewModel = remember(data, callbacks, tracker) {
        HTLDetailInfoCardVM(data, callbacks, tracker)
    }

    val uiState by viewModel.uiState

    HTLDetailInfoCardContent(modifier, uiState)
}

/**
 * Every card will have this content composable which will take ui state only and render UI
 * */
@Composable
fun HTLDetailInfoCardContent(modifier: Modifier = Modifier, uiState: HTLDetailInfoCardUiState) {
    Card(
        modifier = modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HotelTagsRow(tags = uiState.tags)
            HotelNameHeader(hotelName = uiState.hotelName)
            StarRating(rating = uiState.stars)
            HotelSectionsList(sections = uiState.sections)
            HotelDescription(description = uiState.description)
        }
    }
}

/**
 * Displays hotel tags in a horizontal scrollable row
 */
@Composable
private fun HotelTagsRow(tags: List<String>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tags) { tag ->
            HotelTagItem(tag = tag)
        }
    }
}

/**
 * Individual tag item with border styling
 */
@Composable
private fun HotelTagItem(tag: String) {
    Text(
        text = tag,
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 6.dp, vertical = 2.dp)
    )
}

/**
 * Displays the hotel name as a prominent header
 */
@Composable
private fun HotelNameHeader(hotelName: String) {
    Text(
        text = hotelName,
        fontSize = 18.sp,
        fontWeight = FontWeight.Black,
        color = Color.Black
    )
}

/**
 * Displays all hotel sections with dividers between them
 */
@Composable
private fun HotelSectionsList(sections: List<HotelInfoCardSection>) {
    Column {
        sections.forEach { section ->
            section.UI(modifier = Modifier)
            HorizontalDivider()
        }
    }
}

/**
 * Displays the hotel description text
 */
@Composable
private fun HotelDescription(description: String) {
    Text(text = description)
}

@Preview(showBackground = true)
@Composable
fun HTLDetailInfoCardContentPreview() {
    val sampleUiState = HTLDetailInfoCardUiState(
        tags = listOf("Luxury", "Beachfront", "Spa", "Pet Friendly"),
        stars = 4,
        hotelName = "Grand Paradise Resort & Spa",
        sections = listOf(
            HotelInfoCardSection.ReviewRatingsSection(
                starRating = 4.5,
                title = "Excellent (1,234 Ratings)",
                accentColor = Color.Blue,
                subtitle = "Based on verified guest reviews",
                trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                onTrailingIconClicked = {}
            ),
            HotelInfoCardSection.LocationSection(
                iconUrl = Icons.Default.LocationOn,
                title = "Miami Beach | 2.5 miles from airport",
                trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                onTrailingIconClicked = {}
            ),
            HotelInfoCardSection.ChainGroupSection(
                iconUrl = Icons.Default.Home,
                title = "Marriott Hotels | Member Benefits Available",
                trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                onTrailingIconClicked = {}
            )
        ),
        description = "Experience luxury and comfort at our world-class resort. Enjoy stunning ocean views, premium amenities, and exceptional service in the heart of Miami Beach."
    )

    HTLDetailInfoCardContent(uiState = sampleUiState)
}