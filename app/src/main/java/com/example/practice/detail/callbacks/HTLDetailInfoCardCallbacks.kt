package com.example.practice.detail.callbacks

/**
 * This card will contain all the call backs for a specific card,
 * this will eliminate usage of event stream lambdas
 * all event will have its own lambda instead of passing a hotel event and data which later needs typecasting.
 * this will also help cases where a card sends an event but activity is not handling it, happens in dev a lot
 * */
data class HTLDetailInfoCardCallbacks(
    val onReviewRatingsClicked: () -> Unit,
    val onLocationClicked: () -> Unit,
    val onChainGroupClicked: () -> Unit,
    val onPropertyOverViewClicked: () -> Unit
)