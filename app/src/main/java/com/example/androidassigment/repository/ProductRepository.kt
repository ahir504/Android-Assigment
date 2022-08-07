package com.example.androidassigment.repository

import androidx.lifecycle.LiveData
import com.example.androidassigment.dataBase.ProductDao
import com.example.androidassigment.entity.Product
import com.example.androidassigment.model.ProductModel

class ProductRepository(private val productDao: ProductDao) {

    val readAllProductFromDataBase: LiveData<List<Product>> =productDao.readAllProduct()

    fun addProduct(product: Product){
        productDao.addProduct(product)
    }

}