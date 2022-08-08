package com.example.androidassigment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidassigment.dataBase.ProductDataBase
import com.example.androidassigment.entity.*
import com.example.androidassigment.model.Color
import com.example.androidassigment.model.Products
import com.example.androidassigment.model.Store
import com.example.androidassigment.model.StoreModel
import com.example.androidassigment.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository

    init {
        val productDao = ProductDataBase.getInstance(
            application
        ).productDao()
        repository = ProductRepository(productDao)
    }

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }

    fun updateProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateProduct(product)
        }
    }

    fun addStore(stores: Stores) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStore(stores)
        }
    }

    fun addColors(colors: Colors) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addColor(colors)
        }
    }

    fun deleteProductById(productId : Int){
        repository.deleteProductById(productId)
    }

    fun deleteProductStore(productId : Int){
        repository.deleteProductStore(productId)
    }

   fun deleteProductColor(productId : Int){
       repository.deleteProductColor(productId)
   }
    fun addProductAndStoreCrossRef(productStoreCrossRef: ProductStoreCrossRef){
        viewModelScope.launch(Dispatchers.IO){
            repository.addProductAndStoreCrossRef(productStoreCrossRef)
        }
    }

    fun addProductAndColorCrossRef(productAndColorCrossRef: ProductAndColorCrossRef){
        viewModelScope.launch(Dispatchers.IO){
            repository.addProductAndColorCrossRef(productAndColorCrossRef)
        }
    }

    fun readAllColorsById(colorId : Int) : LiveData<List<Color>>{
        return repository.readAllColorsById(colorId)
    }

    fun readAllColors() : LiveData<List<Color>>{
        return repository.readAllColors()
    }

    fun readAllStore() : LiveData<List<StoreModel>>{
        return repository.readAllStore()
    }

    fun readAllStoreById(productId :Int) : LiveData<List<StoreModel>>{
        return repository.readAllStoreById(productId)
    }
    fun getAllProduct(): LiveData<List<Products>>{
        return repository.getAllProduct()
    }

    fun getAllProductWithStore(productId : Int) : LiveData<List<ProductStoreCrossRef>>{
        return repository.getAllProductWithStore(productId)
    }

    fun getAllProductWithColor(productId: Int) : LiveData<List<ProductAndColorCrossRef>> {
        return repository.getALlProductWithColor(productId)
    }
}