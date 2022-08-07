package com.example.androidassigment.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidassigment.entity.Product
import com.example.androidassigment.entity.ProductStoreCrossRef
import com.example.androidassigment.entity.Stores

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStore(stores: Stores)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductAndStoreCrossRef(productStoreCrossRef: ProductStoreCrossRef)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Query("DELETE FROM product")
    fun deleteAllProduct()

    @Query("SELECT * FROM product ORDER BY productId ")
    fun readAllProduct(): LiveData<List<Product>>
}