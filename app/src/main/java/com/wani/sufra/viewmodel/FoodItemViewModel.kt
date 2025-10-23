package com.wani.sufra.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wani.sufra.data.db.FoodItemRepository
import com.wani.sufra.data.model.FoodItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodItemViewModel(context: Context) : ViewModel() {
    private val repo = FoodItemRepository(context)

    private val _items = MutableStateFlow<List<FoodItem>>(repo.getAll())
    val items: StateFlow<List<FoodItem>> = _items

    fun loadItems() {
        viewModelScope.launch {
            _items.value = repo.getAll()
        }
    }

    fun addItem(name: String, qty: Int) {
        viewModelScope.launch {
            repo.insert(FoodItem(name = name, totalQuantity = qty, quantityType = "g", createdAt = 0, updatedOn = 0, picture = "some photo"))
            loadItems()
        }
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            repo.delete(id)
            loadItems()
        }
    }
}
