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



/**
 * This will be a wrapper class for every card
 * This will not contain any business logic
 * This class will act as summary of card - an entry point from which you can see everything regarding a card
 * What events are fired, what data is required from API, what tracking is to be used
 * */
class HotelInfoCard(
    data: HTLDetailInfoCardModel, // raw data from api response
    callbacks: HTLDetailInfoCardCallbacks, // single class with all events concerned with current card
    val tracker: HTLDetailInfoTracker, // card specific tracker which will be later on used by card itself
    override val cardId: Int
): DetailCards(cardId) {

    // Instantiate the State Holder HERE.
    // This persists as long as this 'HotelInfoCard' object is in the list.
    private val viewModel = HTLDetailInfoCardVM(data, callbacks, tracker)

    @Composable
    override fun UI(modifier: Modifier) {
        HTLDetailInfoCard(modifier = Modifier, viewModel = viewModel)
    }
}