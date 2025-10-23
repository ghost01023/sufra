package com.wani.sufra.data.model

data class CollectedConcreteFoodItem (
    val id: Int,
    val createdAt: Int,
    val updatedOn: Int,
    val macroTableId: Int,
    val macroTableColor: String,
    val macroTableCreatedAt: Int,
    val macroTableName: String,
    val foodItemId: Int,
    val foodItemCreatedAt: Int,
    val foodItemName: String,
    val foodItemPicture: String,
    val foodItemQuantityType: String,
    val foodItemTotalQuantity: Int,
    val foodItemUpdatedOn: Int
)