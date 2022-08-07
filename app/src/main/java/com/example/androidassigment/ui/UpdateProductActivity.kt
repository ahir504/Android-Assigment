package com.example.androidassigment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidassigment.databinding.ActivityUpdateProductBinding
import com.example.androidassigment.entity.Product
import com.example.androidassigment.model.ProductModel
import com.example.androidassigment.util.Constant
import com.example.androidassigment.util.Util
import com.example.androidassigment.viewModel.ProductViewModel

class UpdateProductActivity : AppCompatActivity() {
    lateinit var binding : ActivityUpdateProductBinding
    private lateinit var mProductViewModel: ProductViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        val products = Util.getDataFromProductJson(applicationContext,Constant.PRODUCT_FILE_NAME)
        val stores = Util.getDataFromStoreJson(applicationContext,Constant.STORE_FILE_NAME)

        insertProductDataToDataBase(products)


    }

    private fun insertProductDataToDataBase(products: ProductModel?){
        var productFromDataBase : List<Product>? =null
        mProductViewModel.readAllProductViewModel.observe(this){
           productFromDataBase=it
        }

        if(productFromDataBase?.isEmpty() == true) {

            for (product in products!!.products) {
                val productEntity = Product(
                    product.id,
                    product.name,
                    product.description,
                    product.regularPrice,
                    product.salePrice,
                    product.productImage,
                    product.color[0]
                )
                mProductViewModel.addProduct(productEntity)
            }
        }

    }
}