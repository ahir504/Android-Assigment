package com.example.androidassigment.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val productName: String,
    val description: String,
    val regularPrice: Float,
    val salePrice: Float,
    val productImage: String,
)
