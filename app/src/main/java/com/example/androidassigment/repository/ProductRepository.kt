package com.example.androidassigment.repository

import androidx.lifecycle.LiveData
import com.example.androidassigment.dataBase.ProductDao
import com.example.androidassigment.entity.*
import com.example.androidassigment.model.Color
import com.example.androidassigment.model.Products
import com.example.androidassigment.model.StoreModel

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

    fun updateProduct(product: Product){
        productDao.updateProduct(product)
    }


    fun deleteProductById(productId: Int){
        productDao.deleteProductById(productId)
    }

    fun deleteProductStore(productId :Int){
        productDao.deleteProductAndStore(productId)
    }

    fun deleteProductColor(productId: Int){
        productDao.deleteProductAndColor(productId)
    }

    fun addProductAndStoreCrossRef(productStoreCrossRef: ProductStoreCrossRef){
        productDao.addProductAndStoreCrossRef(productStoreCrossRef)
    }

    fun addProductAndColorCrossRef(productAndColorCrossRef: ProductAndColorCrossRef){
        productDao.addProductAndColorCrossRef(productAndColorCrossRef)
    }

    fun readAllColorsById(colorId :Int) : LiveData<List<Color>>{
        return productDao.readAllColorsById(colorId)
    }

    fun readAllColors() : LiveData<List<Color>>{
        return productDao.readAllColors()
    }

    fun readAllStore() : LiveData<List<StoreModel>>{
        return productDao.readAllStores()
    }

    fun readAllStoreById(productId : Int) : LiveData<List<StoreModel>>{
        return productDao.readAllStoresById(productId)
    }
    fun getAllProduct() : LiveData<List<Products>>{
        return productDao.readAllProduct()
    }

    fun getAllProductWithStore(productId : Int) : LiveData<List<ProductStoreCrossRef>> {
        return productDao.getAllProductWithStore(productId)
    }

    fun getALlProductWithColor(productId: Int) : LiveData<List<ProductAndColorCrossRef>> {
        return productDao.getAllProductWithColor(productId)
    }

}