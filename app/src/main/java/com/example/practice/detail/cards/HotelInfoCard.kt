package com.example.practice.detail.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practice.detail.ui.HTLDetailInfoCard
import com.example.practice.detail.viewmodel.HTLDetailInfoCardVM
import com.example.practice.detail.base.DetailCards
import com.example.practice.detail.base.DetailTracker
import com.example.practice.detail.callbacks.HTLDetailInfoCardCallbacks
import com.example.practice.detail.model.HTLDetailInfoCardModel
import com.example.practice.detail.tracker.HTLDetailInfoTracker

class HotelInfoCard(
    val data: HTLDetailInfoCardModel, // raw data from api response
    val callbacks: HTLDetailInfoCardCallbacks, // single class with all events concerned with current card
    val tracker: HTLDetailInfoTracker, // card specific tracker which will be later on used by card itself
    override val cardId: Int
): DetailCards(cardId) {

    @Composable
    override fun UI(modifier: Modifier) {
        HTLDetailInfoCard(modifier = Modifier.Companion, data = data, callbacks = callbacks, tracker = tracker)
    }
}