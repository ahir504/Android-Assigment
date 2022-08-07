package com.example.androidassigment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassigment.adapter.ProductListAdapter
import com.example.androidassigment.databinding.ActivityProductsBinding
import com.example.androidassigment.model.ProductModel

class ProductsActivity : AppCompatActivity() {
   // lateinit var productsModel : ProductModel
    lateinit var binding: ActivityProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val productListAdapter = ProductListAdapter(productsModel)
//        binding.rvProductList.layoutManager = LinearLayoutManager(this)
//        binding.rvProductList.adapter = productListAdapter
    }
}