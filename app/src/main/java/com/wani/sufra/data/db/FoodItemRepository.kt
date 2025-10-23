package com.wani.sufra.data.db

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.wani.sufra.data.model.FoodItem

class FoodItemRepository(context: Context) {
    private val dbHelper = MyDatabaseHelper(context)

    fun insert(item: FoodItem): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", item.name)
            put("total_quantity", item.totalQuantity)
            put("quantity_type", item.quantityType)
        }
        return db.insert("food_items", null, values)
    }

    fun getAll(): List<FoodItem> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM food_items", null)
        val items = mutableListOf<FoodItem>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val createdAt = cursor.getInt(cursor.getColumnIndexOrThrow("created_at"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val picture = cursor.getString(cursor.getColumnIndexOrThrow("picture"))
            val qtyType = cursor.getString(cursor.getColumnIndexOrThrow("quantity_type"))
            val qty = cursor.getInt(cursor.getColumnIndexOrThrow("total_quantity"))
            val updatedOn = cursor.getInt(cursor.getColumnIndexOrThrow("updated_on"))
            val foodItem = FoodItem(id, createdAt, name,
                picture ?: "picture", qtyType, qty, updatedOn)
            items.add(foodItem)
        }
        cursor.close()
        return items
    }

    fun update(item: FoodItem) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", item.name)
            put("total_quantity", item.totalQuantity)
            put("quantity_type", item.quantityType)
            put("picture", item.picture)
            put("updated_on", item.updatedOn)
        }
        db.update("food_items", values, "id=?", arrayOf(item.id.toString()))
    }

    fun delete(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("food_items", "id=?", arrayOf(id.toString()))
    }
}
