package com.example.androidassigment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassigment.adapter.ProductListAdapter
import com.example.androidassigment.databinding.ActivityProductsBinding
import com.example.androidassigment.entity.Colors
import com.example.androidassigment.entity.ProductStoreCrossRef
import com.example.androidassigment.entity.Stores
import com.example.androidassigment.model.*
import com.example.androidassigment.viewModel.ProductViewModel

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        mProductViewModel.getAllProduct().observe(this){
            val productListAdapter = ProductListAdapter(this,it)
            binding.rvProductList.layoutManager = LinearLayoutManager(this)
            binding.rvProductList.adapter = productListAdapter
        }



    }



}