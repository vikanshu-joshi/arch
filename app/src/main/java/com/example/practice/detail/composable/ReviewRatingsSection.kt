package com.example.practice.detail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.detail.state.HotelInfoCardSection

@Composable
fun ReviewRatingsSectionUI(
    section: HotelInfoCardSection.ReviewRatingsSection,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { section.onTrailingIconClicked() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Rating box on the left
        Box(
            modifier = Modifier
                .size(width = 48.dp, height = 40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(section.accentColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = String.format("%.1f", section.starRating),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Title and subtitle in the center
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = section.title,
                fontWeight = FontWeight.Bold,
                color = section.accentColor
            )
            Text(
                text = section.subtitle,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Trailing arrow icon
        Icon(
            imageVector = section.trailingIcon,
            contentDescription = null,
            tint = Color(0xFF2196F3), // Blue color for arrow
            modifier = Modifier.size(20.dp)
        )
    }
}

