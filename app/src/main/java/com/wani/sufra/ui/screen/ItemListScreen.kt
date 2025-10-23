package com.wani.sufra.ui.screen

import ClockDisplay
import android.content.ClipData
import android.content.ClipDescription
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.wani.sufra.viewmodel.FoodItemViewModel
import com.wani.sufra.ui.component.FoodCard
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import com.wani.sufra.data.model.FoodItem
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
                items(foodItems.size) { index ->
                    Log.d("DB_QUERY", "food_item id is " + foodItems[index].id.toString())
                    FoodCard(modifier = Modifier, foodItem = foodItems[index], viewModel = viewModel)
                }
            })


        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event
                        .mimeTypes()
                        .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                },
                target = remember {
                    object : DragAndDropTarget {
                        override fun onDrop(event: DragAndDropEvent): Boolean {
                            val itemId = event.toAndroidDragEvent().clipData?.getItemAt(0)?.text?.toString()?.toIntOrNull()
                            if (itemId == null) {
                                return false
                            }
                            Log.d("DRAG_EVENT", "foodItemId is $itemId")
                            viewModel.deleteItem(itemId)
                            return true
                        }
                    }
                }
            )
        )
        {
            Text("Drag item here to delete it")
        }

//        Path().addArc(Rect(0f, 0f, 100f, 100f), 0f, 360f)
    }
}
