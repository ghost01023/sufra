package com.wani.sufra.data.model

data class FoodItem(
    val id: Int = 0,
    val createdAt: Int,
    val name: String,
    val picture: String,
    val quantityType: String,
    val totalQuantity: Int,
    val updatedOn: Int
)