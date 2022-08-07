package com.example.androidassigment.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StoreWithProduct(
    @Embedded val stores : Stores,
    @Relation(
        parentColumn = "productId",
        entityColumn = "storeId",
        associateBy = Junction(ProductStoreCrossRef::class)
    )
    val products : List<Product>
)
