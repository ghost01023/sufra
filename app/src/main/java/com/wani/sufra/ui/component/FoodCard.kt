package com.wani.sufra.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun FoodCard( name: String, totalQuantity: Int, quantityEaten: Int, unit: String, modifier: Modifier) {
    Column(modifier =  modifier) {
        Text(name)
        Text(totalQuantity.toString())
        Text(quantityEaten.toString())
        Text(unit)
    }
}