package com.example.androidassigment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidassigment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btShowProduct.setOnClickListener{
            startActivity( Intent(this, ProductsActivity::class.java))
        }

        binding.btCreateProduct.setOnClickListener{
            startActivity(Intent(this,UpdateProductActivity::class.java))
        }


    }
}