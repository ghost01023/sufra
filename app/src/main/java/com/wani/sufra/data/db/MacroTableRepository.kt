package com.wani.sufra.data.db

import android.content.ContentValues
import android.content.Context
import com.wani.sufra.data.model.MacroTable

class MacroTableRepository(context: Context) {
    private val dbHelper = MyDatabaseHelper(context)

    fun insert(item: MacroTable): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", item.name)
            put("created_at", item.createdAt)
            put("color", item.color)
        }
        return db.insert("items", null, values)
    }

    fun getAll(): List<MacroTable> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM items", null)
        val items = mutableListOf<MacroTable>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val createdAt = cursor.getInt(cursor.getColumnIndexOrThrow("created_at"))
            val color = cursor.getString(cursor.getColumnIndexOrThrow("color"))
            items.add(MacroTable(id, color, createdAt, name))
        }
        cursor.close()
        return items
    }

    fun update(item: MacroTable) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", item.name)
            put("color", item.color)
        }
        db.update("items", values, "id=?", arrayOf(item.id.toString()))
    }

    fun delete(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("items", "id=?", arrayOf(id.toString()))
    }
}
