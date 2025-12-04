package com.example.practice.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.example.practice.detail.base.DetailCards

@Composable
fun HotelDetailScreen(
    cards: SnapshotStateList<DetailCards>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = cards, key = { card ->
            card.cardId
        }) { card ->
            card.UI(modifier = Modifier)
        }
    }
}