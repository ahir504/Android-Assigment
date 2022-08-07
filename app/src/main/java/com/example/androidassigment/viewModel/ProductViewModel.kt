package com.example.androidassigment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidassigment.dataBase.ProductDataBase
import com.example.androidassigment.entity.Product
import com.example.androidassigment.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    val readAllProductViewModel : LiveData<List<Product>>
    private val repository : ProductRepository

    init {
        val productDao = ProductDataBase.getInstance(
            application
        ).productDao()

        repository= ProductRepository(productDao)
        readAllProductViewModel = repository.readAllProductFromDataBase


    }

    fun addProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }

}