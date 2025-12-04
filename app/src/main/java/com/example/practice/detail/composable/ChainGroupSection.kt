package com.example.practice.detail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import com.example.practice.detail.state.HotelInfoCardSection

@Composable
fun ChainGroupSectionUI(
    section: HotelInfoCardSection.ChainGroupSection,
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
        // Brand logo box on the left with gold border
        Box(
            modifier = Modifier
                .size(width = 48.dp, height = 40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = section.iconUrl,
                contentDescription = "Brand logo",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Group text in the center
        Text(
            text = section.title,
            modifier = Modifier.weight(1f),
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Trailing arrow icon
        section.trailingIcon?.let { icon ->
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF2196F3), // Blue color for arrow
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

