package com.example.androidassigment.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidassigment.databinding.ActivityUpdateProductBinding
import com.example.androidassigment.entity.*
import com.example.androidassigment.model.*
import com.example.androidassigment.util.Constant
import com.example.androidassigment.viewModel.ProductViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.IOException


class UpdateProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateProductBinding
    private lateinit var products: Products
    private lateinit var mProductViewModel: ProductViewModel
    private var colorModel = listOf<Color>()
    private var storeModel = listOf<StoreModel>()
    private var from = 0
    private var imagePath = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        from = intent.getIntExtra(Constant.FROM, 0)
        if (from == 1) {
            products = intent.getSerializableExtra(Constant.PRODUCT_MODEL) as Products
            binding.tilProductName.editText!!.text.insert(0, products.productName)
            binding.tilProductDescription.editText!!.text.insert(0, products.description)
            binding.tilProductPrice.editText!!.text.insert(0, products.regularPrice.toString())
            binding.tilProductSalePrice.editText!!.text.insert(0, products.salePrice.toString())

            binding.btSelectStore.visibility = View.VISIBLE
            binding.btSelectColor.visibility = View.VISIBLE
        } else {
            binding.btSelectStore.visibility = View.GONE
            binding.btSelectColor.visibility = View.GONE
        }
        mProductViewModel.readAllColors().observe(this) {
            colorModel = it
        }
        mProductViewModel.readAllStore().observe(this) {
            storeModel = it
        }


        if (from == 0) {
            binding.btUpdateProduct.setOnClickListener {
                if (imagePath.isEmpty()) {
                    Snackbar.make(binding.layout, "Please Add Image First", Snackbar.LENGTH_LONG)
                        .show()
                } else {
                    insertProductDataToDataBase(storeModel, colorModel)
                }
            }
        } else {
            binding.btUpdateProduct.setOnClickListener {
                updateData()
            }
        }
        binding.btSelectImg.setOnClickListener {
            imageChooser()
        }
    }

    private fun updateData() {
        val productEntity = Product(
            products.productId,
            binding.tilProductName.editText!!.text.toString(),
            binding.tilProductDescription.editText!!.text.toString(),
            binding.tilProductPrice.editText!!.text.toString().toFloat(),
            binding.tilProductSalePrice.editText!!.text.toString().toFloat(),
            imagePath
        )
        mProductViewModel.updateProduct(productEntity)
        startActivity(Intent(this, ProductsActivity::class.java))
    }

    private fun insertProductDataToDataBase(store: List<StoreModel>?, colorModel: List<Color>?) {
        Log.d("tab", imagePath)

        val productEntity = Product(
            0,
            binding.tilProductName.editText!!.text.toString(),
            binding.tilProductDescription.editText!!.text.toString(),
            binding.tilProductPrice.editText!!.text.toString().toFloat(),
            binding.tilProductSalePrice.editText!!.text.toString().toFloat(),
            imagePath
        )

        if (from == 1) {
            for (i in 1..3) {
                val productStoreCrossRef =
                    ProductStoreCrossRef(products.productId, store!!.random().storeId)
                val productColorCrossRef =
                    ProductAndColorCrossRef(products.productId, colorModel!!.random().colorId)

                mProductViewModel.addProductAndColorCrossRef(productColorCrossRef)
                mProductViewModel.addProductAndStoreCrossRef(productStoreCrossRef)
            }
        }
        mProductViewModel.addProduct(productEntity)

        startActivity(Intent(this, ProductsActivity::class.java))

    }

    private fun imageChooser() {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT.apply {
            i.addCategory( Intent.CATEGORY_OPENABLE)
        }

        launchSomeActivity.launch(i)
    }

    private var launchSomeActivity =
        registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if (data != null && data.data != null) {
                    val selectedImageUri: Uri? = data.data
                    val selectedImageBitmap: Bitmap
                    imagePath = selectedImageUri.toString()
                    try {
                        selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                            this.contentResolver,
                            selectedImageUri
                        )
                        binding.ivProductImg.setImageBitmap(
                            selectedImageBitmap
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

}