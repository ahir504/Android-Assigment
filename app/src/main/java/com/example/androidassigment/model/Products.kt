package com.example.androidassigment.model

data class Products(
    val color: List<String>,
    val description: String,
    val id: Int,
    val name: String,
    val productImage: String,
    val regularPrice: Float,
    val salePrice: Float,
    val storeModel: List<StoreModel>
)