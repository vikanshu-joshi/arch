package com.example.practice.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practice.detail.base.DetailCards

@Composable
fun HotelDetailScreen(
    cards: List<DetailCards>
) {
    cards.forEach { card ->
        card.UI(modifier = Modifier)
    }
}