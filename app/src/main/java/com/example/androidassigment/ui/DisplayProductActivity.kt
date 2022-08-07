package com.example.androidassigment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidassigment.databinding.ActivityDisplayProductBinding

class DisplayProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btDeleteProduct.setOnClickListener{

        }

        binding.btUpdateProduct.setOnClickListener {
            startActivity(Intent(applicationContext,UpdateProductActivity::class.java))
        }
    }
}