package com.example.androidassigment.entity

import androidx.room.*

data class ProductWithColor(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "productId",
        entityColumn = "colorId",
        associateBy = Junction(ProductAndColorCrossRef::class)
    )
    val colors: List<Colors>
)
