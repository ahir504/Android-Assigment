package com.example.androidassigment.entity

import androidx.room.Entity

@Entity(primaryKeys = ["productId","storeId"])
data class ProductStoreCrossRef(
    val productId : Int,
    val storeId : Int
)
