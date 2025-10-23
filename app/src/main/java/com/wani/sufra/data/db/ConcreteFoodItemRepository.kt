package com.wani.sufra.data.db


import android.content.ContentValues
import android.content.Context
import com.wani.sufra.data.model.ConcreteFoodItem

class ConcreteFoodItemRepository(context: Context) {
    private val dbHelper = MyDatabaseHelper(context)

    fun insert(item: ConcreteFoodItem): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("item_id", item.itemId)
            put("macro_table_id", item.macroTableId)
            put("created_at", item.createdAt)
            put("updated_on", item.updatedOn)
        }
        return db.insert("items", null, values)
    }
    fun getAllForMacroTable(macroTableId: Int): List<ConcreteFoodItem> {
        val db = dbHelper.readableDatabase
        val query = """SELECT * FROM items WHERE macro_table_id = ?"""
        val stringParams = arrayOf(macroTableId.toString())
        val cursor = db.rawQuery(query, stringParams)
        val items = mutableListOf<ConcreteFoodItem>()
        while (cursor.moveToNext()) {

        }
        cursor.close()
        return items
    }
//    fun getAll(): List<ConcreteFoodItem> {
//        val db = dbHelper.readableDatabase
//        val cursor = db.rawQuery("SELECT * FROM items", null)
//        val items = mutableListOf<ConcreteFoodItem>()
//        while (cursor.moveToNext()) {
//            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
//            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
//            val qty = cursor.getInt(cursor.getColumnIndexOrThrow("total_quantity"))
//            val qtyType = cursor.getString(cursor.getColumnIndexOrThrow("quantity_type"))
//            val picture = cursor.getString(cursor.getColumnIndexOrThrow("picture"))
//            val createdAt = cursor.getInt(cursor.getColumnIndexOrThrow("created_at"))
//            val updatedOn = cursor.getInt(cursor.getColumnIndexOrThrow("updated_on"))
//            items.add(ConcreteFoodItem(id, name, qty, qtyType, picture, createdAt, updatedOn))
//        }
//        cursor.close()
//        return items
//    }
}


/*
class FoodItemRepository(context: Context) {




    fun update(item: FoodItem) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", item.name)
            put("total_quantity", item.total_quantity)
            put("quantity_type", item.quantity_type)
            put("picture", item.picture)
            put("updated_on", item.updated_on)
        }
        db.update("items", values, "id=?", arrayOf(item.id.toString()))
    }

    fun delete(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("items", "id=?", arrayOf(id.toString()))
    }
}



 */