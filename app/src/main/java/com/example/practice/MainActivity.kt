package com.example.practice

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.practice.detail.DetailsCards
import com.example.practice.detail.HotelDetailScreen
import com.example.practice.detail.callbacks.HTLDetailInfoCardCallbacks
import com.example.practice.detail.model.HTLDetailInfoCardModel
import com.example.practice.detail.ui.HTLDetailInfoCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val cards = mutableListOf<DetailsCards>()
                    cards.add(
                        DetailsCards.HTLDetailInfoCard(
                            data = getDummyHTLDetailInfoCardModel(),
                            callbacks = getHTLDetailInfoCardCallbacks(this@MainActivity),
                            cardId = 0
                        )
                    )
                    HotelDetailScreen(cards)
                }
            }
        }
    }
}


/**
 * This method mimics the response received from API
 * */
fun getDummyHTLDetailInfoCardModel(): HTLDetailInfoCardModel {
    return HTLDetailInfoCardModel(
        tags = listOf("Luxury", "Beachfront", "Spa", "Pet Friendly", "Pool"),
        hotelName = "Grand Paradise Resort & Spa",
        rating = 4.5,
        ratingsType = "Excellent",
        totalRatings = 1234,
        ratingsInfoText = "Based on verified guest reviews",
        locationIcon = "location_icon",
        locationName = "Miami Beach",
        locationInfo = "2.5 miles from airport",
        chainName = "Marriott Hotels",
        chainIcon = "chain_icon",
        description = "Experience luxury and comfort at our world-class resort. Enjoy stunning ocean views, premium amenities, and exceptional service in the heart of Miami Beach. Our resort features multiple restaurants, a full-service spa, and direct beach access."
    )
}

/**
 * This method mimics event handling related to a card
 * */
fun getHTLDetailInfoCardCallbacks(context: Context): HTLDetailInfoCardCallbacks {
    return HTLDetailInfoCardCallbacks(
        onReviewRatingsClicked = {
            Toast.makeText(context, "Review Ratings Clicked", Toast.LENGTH_SHORT).show()
        },
        onLocationClicked = {
            Toast.makeText(context, "Location Clicked", Toast.LENGTH_SHORT).show()
        },
        onChainGroupClicked = {
            Toast.makeText(context, "Chain Group Clicked", Toast.LENGTH_SHORT).show()
        },
        onPropertyOverViewClicked = {
            Toast.makeText(context, "Property Overview Clicked", Toast.LENGTH_SHORT).show()
        }
    )
}
