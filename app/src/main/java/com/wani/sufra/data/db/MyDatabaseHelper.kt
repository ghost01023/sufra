package com.wani.sufra.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, "sufra.sqlite", null, 1
) {
    override fun onCreate(db: SQLiteDatabase) {
        Log.d("MyDatabaseHelper", "onCreate called")

        db.execSQL("DROP TABLE IF EXISTS food_items")

        db.execSQL(
            """
            CREATE TABLE food_items (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                total_quantity INTEGER NOT NULL,
                quantity_type TEXT NOT NULL,
                created_at INTEGER DEFAULT (STRFTIME('%s', 'now')),
                updated_on INTEGER DEFAULT (STRFTIME('%s', 'now')),
                picture BLOB DEFAULT NULL
            )
            """.trimIndent()
        )
        Log.d("MyDatabaseHelper", "food_items table created")


        db.execSQL(
            """
                CREATE TABLE macro_tables (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                created_at INTEGER DEFAULT (STRFTIME('%s', 'now')),
                color TEXT DEFAULT "white",
                name TEXT NOT NULL
                )
            """.trimIndent()
        )
        Log.d("MyDatabaseHelper", "macro_tables table created")


        db.execSQL(
            """
                CREATE TABLE concrete_items (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                created_at INTEGER DEFAULT (STRFTIME('%s', 'now')),
                updated_on INTEGER DEFAULT (STRFTIME('%s', 'now')),
                food_item_id INTEGER NOT NULL,
                macro_table_id INTEGER NOT NULL,
                FOREIGN KEY (food_item_id) REFERENCES food_items(id),
                FOREIGN KEY (macro_table_id) REFERENCES macro_tables(id)
                )
            """.trimIndent()
        )
        Log.d("MyDatabaseHelper", "concrete_items table created")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS items")
        db.execSQL("DROP TABLE IF EXISTS food_items")
        onCreate(db)
    }
}
