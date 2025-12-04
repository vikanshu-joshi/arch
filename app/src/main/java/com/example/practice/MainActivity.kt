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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.practice.detail.HotelDetailScreen
import com.example.practice.detail.callbacks.HTLDetailInfoCardCallbacks
import com.example.practice.detail.model.HTLDetailInfoCardModel
import com.example.practice.detail.viewmodel.HTLDetailInfoCardVM
import com.example.practice.detail.cards.HotelInfoCard
import com.example.practice.detail.base.DetailCards
import com.example.practice.detail.base.DetailTracker
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { padding ->

                val cards = SnapshotStateList<DetailCards>() // creating this cards list is role of activity view model based on api response

                LaunchedEffect(Unit) {
                    cards.clear()
                    delay(3000)
                    cards.add(
                        HotelInfoCard(
                            viewModel = HTLDetailInfoCardVM(
                                getDummyHTLDetailInfoCardModel(),
                                HTLDetailInfoCardCallbacks(
                                    onReviewRatingsClicked = ::onReviewRatingsClicked,
                                    onLocationClicked = ::onLocationClicked,
                                    onChainGroupClicked = ::onChainGroupClicked,
                                ),
                                detailTracker = DetailTracker()
                            ),
                            cardId = 0
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HotelDetailScreen(cards)
                }
            }
        }
    }


    fun onReviewRatingsClicked() {
        Toast.makeText(this, "review ratings clicked", Toast.LENGTH_LONG).show()
    }

    fun onLocationClicked() {
        Toast.makeText(this, "location clicked", Toast.LENGTH_LONG).show()
    }

    fun onChainGroupClicked() {
        Toast.makeText(this, "chain group clicked", Toast.LENGTH_LONG).show()
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
