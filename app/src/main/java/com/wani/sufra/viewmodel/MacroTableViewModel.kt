package com.wani.sufra.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wani.sufra.data.db.MacroTableRepository
import com.wani.sufra.data.model.MacroTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MacroTableViewModel(context: Context) : ViewModel() {
    private val repo = MacroTableRepository(context)

    private val _items = MutableStateFlow<List<MacroTable>>(repo.getAll())
    val items: StateFlow<List<MacroTable>> = _items

    fun loadItems() {
        viewModelScope.launch {
            _items.value = repo.getAll()
        }
    }

    fun addItem(name: String, qty: Int) {
        viewModelScope.launch {
            repo.insert(MacroTable(name = name, color = "white", createdAt = 0))
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
