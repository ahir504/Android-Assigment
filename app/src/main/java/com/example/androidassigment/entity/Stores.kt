package com.example.androidassigment.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stores(
    @PrimaryKey(autoGenerate = true)
    val storeId : Int,
    val storeName : String
)
