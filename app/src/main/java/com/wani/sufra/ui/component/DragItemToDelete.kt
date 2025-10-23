package com.wani.sufra.ui.component

import android.content.ClipDescription
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.unit.dp
import com.wani.sufra.viewmodel.FoodItemViewModel

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun DragItemToDelete(viewModel: FoodItemViewModel) {
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
}