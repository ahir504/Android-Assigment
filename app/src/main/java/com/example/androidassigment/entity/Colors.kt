package com.example.androidassigment.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Colors(
    @PrimaryKey(autoGenerate = true)
    val colorId : Int,
    val colorName :String
)
