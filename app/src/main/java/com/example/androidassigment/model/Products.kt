package com.example.androidassigment.model

import java.io.Serializable

data class Products(
    val productId: Int,
    val productName: String,
    val description: String,
    val regularPrice: Float,
    val salePrice: Float,
    val productImage: String
) : Serializable