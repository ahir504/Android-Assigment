package com.example.androidassigment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.androidassigment.dataBase.ProductDao
import com.example.androidassigment.entity.*
import com.example.androidassigment.model.ProductModel
import com.example.androidassigment.util.Constant
import com.example.androidassigment.util.Util

class ProductRepository(private val productDao: ProductDao) {


    fun addProduct(product: Product) {
        productDao.addProduct(product)
    }

    fun addStore(store: Stores) {
        productDao.addStore(store)
    }

    fun addColor(color: Colors) {
        productDao.addColor(color)
    }

    fun addProductAndStoreCrossRef(productStoreCrossRef: ProductStoreCrossRef){
        productDao.addProductAndStoreCrossRef(productStoreCrossRef)
    }

    fun addProductAndColorCrossRef(productAndColorCrossRef: ProductAndColorCrossRef){
        productDao.addProductAndColorCrossRef(productAndColorCrossRef)
    }

    fun getAllProduct() : LiveData<List<Product>>{
        return productDao.readAllProduct()
    }

    fun getAllProductWithStore(productId : Int) : LiveData<List<ProductStoreCrossRef>> {
        return productDao.getAllProductWithStore(productId)
    }

    fun getALlProductWithColor(productId: Int) : LiveData<List<ProductAndColorCrossRef>> {
        return productDao.getAllProductWithColor(productId)
    }

}