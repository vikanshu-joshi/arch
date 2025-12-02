package com.example.practice.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HotelDetailScreen(
    cards: List<DetailsCards>
) {
    cards.forEach { card ->
        card.UI(modifier = Modifier)
    }
}