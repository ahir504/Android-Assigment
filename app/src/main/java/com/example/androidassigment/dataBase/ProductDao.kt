package com.example.androidassigment.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidassigment.entity.*
import com.example.androidassigment.model.Color
import com.example.androidassigment.model.Products
import com.example.androidassigment.model.StoreModel

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

    @Update
    fun updateStore(stores: Stores)

    @Update
    fun updateColor(colors: Colors)

    @Update
    fun updateProductAndColorCrossRef(productAndColorCrossRef: ProductAndColorCrossRef)

    @Update
    fun updateProductAndStoreCrossRef(productStoreCrossRef: ProductStoreCrossRef)

    @Delete
    fun deleteProduct(product: Product)

    @Transaction
    @Query("DELETE FROM product WHERE productId =:productId")
    fun deleteProductById(productId: Int)

    @Transaction
    @Query("DELETE FROM productstorecrossref WHERE productId =:productId")
    fun deleteProductAndStore(productId: Int)

    @Transaction
    @Query("DELETE FROM productandcolorcrossref WHERE productId =:productId")
    fun deleteProductAndColor(productId: Int)

    @Transaction
    @Query("SELECT * FROM product")
    fun readAllProduct(): LiveData<List<Products>>

    @Transaction
    @Query("SELECT * FROM stores WHERE storeId = :storeId")
    fun readAllStoresById(storeId: Int): LiveData<List<StoreModel>>

    @Transaction
    @Query("SELECT * FROM colors")
    fun readAllColors(): LiveData<List<Color>>

    @Transaction
    @Query("SELECT * FROM stores")
    fun readAllStores(): LiveData<List<StoreModel>>

    @Transaction
    @Query("SELECT * FROM colors WHERE colorId = :colorId")
    fun readAllColorsById(colorId: Int): LiveData<List<Color>>

    @Transaction
    @Query("SELECT * FROM productstorecrossref WHERE productId = :productId")
    fun getAllProductWithStore(productId: Int): LiveData<List<ProductStoreCrossRef>>

    @Transaction
    @Query("SELECT * FROM productandcolorcrossref WHERE productId = :productId")
    fun getAllProductWithColor(productId: Int): LiveData<List<ProductAndColorCrossRef>>

}