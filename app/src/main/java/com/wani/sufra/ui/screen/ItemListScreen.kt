package com.wani.sufra.ui.screen

import ClockDisplay
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wani.sufra.ui.component.DragItemToDelete
import com.wani.sufra.viewmodel.FoodItemViewModel
import com.wani.sufra.ui.component.FoodCard
import com.wani.sufra.ui.component.TableChoice



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemListScreen(viewModel: FoodItemViewModel) {
    val foodItems by viewModel.items.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .padding(top = 20.dp)) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ClockDisplay()
            TableChoice()
        }
        Button(onClick = { viewModel.addItem("Apples", 5) }) { Text("Add Sample Item") }


        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp),
            content = {
                items(
                    items = foodItems,
                    key = { item -> item.id }
                ) { foodItem ->
                    FoodCard(modifier = Modifier, foodItem = foodItem, viewModel = viewModel)
                }
            })

        DragItemToDelete(viewModel)
    }
}
