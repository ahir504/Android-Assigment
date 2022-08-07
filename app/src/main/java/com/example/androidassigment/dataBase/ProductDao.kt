package com.example.androidassigment.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidassigment.entity.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStore(stores: Stores)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addColor(colors: Colors)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductAndStoreCrossRef(productStoreCrossRef: ProductStoreCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductAndColorCrossRef(productAndColorCrossRef: ProductAndColorCrossRef)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Transaction
    @Query("DELETE FROM product")
    fun deleteAllProduct()

    @Transaction
    @Query("SELECT * FROM product ORDER BY productId ")
    fun readAllProduct(): LiveData<List<Product>>

    @Transaction
    @Query("SELECT * FROM stores")
    fun readAllStores(): LiveData<List<Stores>>

    @Transaction
    @Query("SELECT * FROM colors")
    fun readAllColors(): LiveData<List<Colors>>

    @Transaction
    @Query("SELECT * FROM productstorecrossref WHERE productId = :productId")
    fun getAllProductWithStore(productId : Int): LiveData<List<ProductStoreCrossRef>>

    @Transaction
    @Query("SELECT * FROM productandcolorcrossref WHERE productId = :productId")
    fun getAllProductWithColor(productId: Int): LiveData<List<ProductAndColorCrossRef>>



}