package com.example.androidassigment.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ColorWithProduct(
    @Embedded val colors :Colors,
    @Relation(
        parentColumn = "colorId",
        entityColumn = "productId",
        associateBy = Junction(ProductAndColorCrossRef::class)
    )
    val products : List<Product>

)
