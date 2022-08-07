package com.example.androidassigment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidassigment.dataBase.ProductDataBase
import com.example.androidassigment.entity.*
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

    fun getAllProduct(): LiveData<List<Product>>{
        return repository.getAllProduct()
    }

    fun getAllProductWithStore(productId : Int) : LiveData<List<ProductStoreCrossRef>>{
        return repository.getAllProductWithStore(productId)
    }

    fun getAllProductWithColor(productId: Int) : LiveData<List<ProductAndColorCrossRef>> {
        return repository.getALlProductWithColor(productId)
    }
}