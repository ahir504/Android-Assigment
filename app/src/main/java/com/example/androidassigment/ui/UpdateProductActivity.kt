package com.example.androidassigment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.androidassigment.databinding.ActivityUpdateProductBinding
import com.example.androidassigment.entity.*
import com.example.androidassigment.model.ColorModel
import com.example.androidassigment.model.ProductModel
import com.example.androidassigment.model.Store
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

        val productsFromJSON = Util.getDataFromProductJson(applicationContext,Constant.PRODUCT_FILE_NAME)
        val storesFromJSON = Util.getDataFromStoreJson(applicationContext,Constant.STORE_FILE_NAME)
        val colorsFromJSON = Util.getDataFromColorJson(applicationContext,Constant.COLOR_FILE_NAME)

        insertProductDataToDataBase(productsFromJSON,storesFromJSON,colorsFromJSON)
        insertStoreDataToDataBase(storesFromJSON)
        insertColorDataToDataBase(colorsFromJSON)

       mProductViewModel.getAllProduct().observe(this){
           Log.d("TAG", it[2].productId.toString())
       }

    }

    private fun insertProductDataToDataBase(products: ProductModel?, store: Store?, colorModel: ColorModel?){
            for (product in products!!.products) {
                val productEntity = Product(
                    product.id,
                    product.name,
                    product.description,
                    product.regularPrice,
                    product.salePrice,
                    product.productImage,
                )
                for (i in products!!.products){
                val productStoreCrossRef =
                    ProductStoreCrossRef(product.id, store!!.stores.random().storeId)
                val productColorCrossRef =
                    ProductAndColorCrossRef(product.id, colorModel!!.color.random().colorId)

                mProductViewModel.addProductAndColorCrossRef(productColorCrossRef)
                mProductViewModel.addProductAndStoreCrossRef(productStoreCrossRef)
            }
                mProductViewModel.addProduct(productEntity)

            }
    }

    private fun insertStoreDataToDataBase(store: Store?){
        for(stores in store!!.stores){
            val storesEntity = Stores(
                stores.storeId,
                stores.storeName
            )
            mProductViewModel.addStore(storesEntity)
        }

    }

    private fun insertColorDataToDataBase(colorModel: ColorModel?){

        for(color in colorModel!!.color){
            val colorEntity = Colors(
                color.colorId,
                color.colorName
            )
            mProductViewModel.addColors(colorEntity)
        }
    }


}