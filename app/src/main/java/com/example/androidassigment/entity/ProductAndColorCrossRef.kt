package com.example.androidassigment.entity

import androidx.room.Entity

@Entity(primaryKeys = ["productId","colorId"])
data class ProductAndColorCrossRef(
    val productId : Int,
    val colorId : Int
)