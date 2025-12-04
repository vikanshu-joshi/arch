package com.example.practice.detail.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practice.detail.ui.HTLDetailInfoCard
import com.example.practice.detail.viewmodel.HTLDetailInfoCardVM
import com.example.practice.detail.base.DetailCards

class HotelInfoCard(
    val viewModel: HTLDetailInfoCardVM,
    override val cardId: Int
): DetailCards(cardId) {

    @Composable
    override fun UI(modifier: Modifier) {
        HTLDetailInfoCard(modifier = Modifier.Companion, viewModel = viewModel)
    }
}