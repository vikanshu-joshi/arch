package com.example.practice.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practice.detail.callbacks.HTLDetailInfoCardCallbacks
import com.example.practice.detail.model.HTLDetailInfoCardModel
import com.example.practice.detail.ui.HTLDetailInfoCard


/**
 * This class will contain all detail page cards
 * Each card will have a data model which will contain the params required from API response
 * Each card will individually create UI from that raw response nodes required
 * Each card will also have a callbacks class which will contain all callbacks that require activity level intervention
 * Rest of the events that do not require activity level intervention will be handled by card itself in VM
 * */
sealed class DetailsCards(
    open val cardId: Int
) {

    data class HTLDetailInfoCard(
        val data: HTLDetailInfoCardModel,
        val callbacks: HTLDetailInfoCardCallbacks,
        override val cardId: Int
    ): DetailsCards(cardId) {
        @Composable
        override fun UI(modifier: Modifier) {
            HTLDetailInfoCard(modifier = Modifier, data = data, callbacks = callbacks)
        }
    }

    // rest of the cards

    @Composable
    abstract fun UI(modifier: Modifier)
}