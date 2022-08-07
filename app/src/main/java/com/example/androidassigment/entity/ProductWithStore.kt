package com.example.androidassigment.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ProductWithStore(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "productId",
        entityColumn = "storeId",
        associateBy = Junction(ProductStoreCrossRef::class)
    )
    val stores : List<Stores>
)
