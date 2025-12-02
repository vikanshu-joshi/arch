package com.example.practice.detail.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StarRating(
    rating: Int,
    maxStars: Int = 5,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        repeat(maxStars) { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(16.dp), // Adjust size as needed
                // If the current index is less than the rating, it's Black, otherwise Gray
                tint = if (index < rating) Color.Black else Color.Gray
            )
        }
    }
}


@Preview
@Composable
fun StarRatingPreview(modifier: Modifier = Modifier) {
    StarRating(rating = 3)
}