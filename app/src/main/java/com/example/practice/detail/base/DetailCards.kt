package com.example.practice.detail.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class DetailCards(
    open val cardId: Int
) {

    @Composable
    abstract fun UI(modifier: Modifier)

}