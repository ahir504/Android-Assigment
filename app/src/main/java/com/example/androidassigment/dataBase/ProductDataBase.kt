package com.example.androidassigment.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidassigment.entity.Product
import com.example.androidassigment.entity.ProductStoreCrossRef
import com.example.androidassigment.entity.Stores
import com.example.androidassigment.util.Constant

@Database(
    entities = [Product::class, Stores::class, ProductStoreCrossRef::class],
    version = 2,
    exportSchema = false
)
abstract class ProductDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDataBase? = null

        fun getInstance(context: Context): ProductDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDataBase::class.java,
                    Constant.DATABASE
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
                INSTANCE = instance
                return instance
            }
        }

    }
}