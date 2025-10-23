package com.wani.sufra.ui.component

import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.unit.dp
import com.wani.sufra.data.model.FoodItem
import com.wani.sufra.viewmodel.FoodItemViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodCard(modifier: Modifier = Modifier, foodItem: FoodItem, viewModel: FoodItemViewModel) {
    Log.d("FOOD_CARD", "Generating food card with id = " + foodItem.id.toString())
    Column(modifier = modifier
        .padding(8.dp)
        .dragAndDropSource {
            detectTapGestures(
                onLongPress = { offset ->
                        Log.d("FoodItemRepository", "deleting with id " + foodItem.id.toString())
                        startTransfer(
                            transferData = DragAndDropTransferData(
                                clipData = ClipData.newPlainText(
                                    "foodItemId", foodItem.id.toString()
                                )
                            )
                        )
                }
            )
        }
    ) {
        Text(foodItem.name)
        Text(foodItem.id.toString())
        Text(foodItem.totalQuantity.toString())
        Text(foodItem.quantityType)
        Button(
            onClick = { viewModel.deleteItem(foodItem.id) },
            modifier = Modifier.padding(4.dp)
        ) {
            Text("Delete ${foodItem.id}")
        }
    }
}