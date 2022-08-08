package com.example.androidassigment.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassigment.adapter.StoreListAdapter
import com.example.androidassigment.databinding.ActivityDisplayProductBinding
import com.example.androidassigment.model.Color
import com.example.androidassigment.model.Products
import com.example.androidassigment.model.StoreModel
import com.example.androidassigment.util.Constant
import com.example.androidassigment.viewModel.ProductViewModel
import java.io.IOException


class DisplayProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayProductBinding
    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var products: Products
    var storeList: MutableList<StoreModel> = mutableListOf()
    var colorList: MutableList<Color> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        products = intent.getSerializableExtra(Constant.PRODUCT_MODEL) as Products

        setContent()

        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        mProductViewModel.getAllProductWithStore(products.productId).observe(this) {

            for (allProductStore in it) {
                mProductViewModel.readAllStoreById(allProductStore.storeId)
                    .observe(this) { productStoreObserver ->
                        for (allStore in productStoreObserver) {
                            val storeModel = StoreModel(
                                allStore.storeId,
                                allStore.storeName
                            )
                            storeList.add(storeModel)
                            val storeListAdapter = StoreListAdapter(this, storeList)
                            binding.rvStoreList.layoutManager = LinearLayoutManager(this)
                            binding.rvStoreList.adapter = storeListAdapter
                        }
                    }
            }
        }

        mProductViewModel.getAllProductWithColor(products.productId).observe(this) {
            for (allProductColor in it) {
                mProductViewModel.readAllColorsById(allProductColor.colorId)
                    .observe(this) { productAllColor ->
                        for (allColor in productAllColor) {
                            val color = Color(
                                allColor.colorId,
                                allColor.colorName
                            )
                            colorList.add(color)
                        }
                    }
            }
        }


        binding.btUpdateProduct.setOnClickListener {

            val intent = Intent(this, UpdateProductActivity::class.java)
            intent.putExtra(Constant.PRODUCT_MODEL, products)
            intent.putExtra(Constant.FROM, 1)
            startActivity(intent)
            finish()
        }

        binding.btDeleteProduct.setOnClickListener {
            mProductViewModel.deleteProductById(products.productId)
            mProductViewModel.deleteProductColor(products.productId)
            mProductViewModel.deleteProductStore(products.productId)
            startActivity(Intent(this, ProductsActivity::class.java))
            finish()
        }

    }

    @SuppressLint("WrongConstant")
    private fun setContent() {

        if(products.productImage.isEmpty()){

        }else {
            // set image to image view
            val selectedImageUri = Uri.parse(products.productImage)
            val selectedImageBitmap: Bitmap
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            val takeFlags: Int =
                (intent.flags.and(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION))
            try {
                application.grantUriPermission(
                    application.packageName,
                    selectedImageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                application.revokeUriPermission(selectedImageUri, takeFlags)
                application.contentResolver.takePersistableUriPermission(
                    selectedImageUri,
                    takeFlags
                )
                selectedImageBitmap = BitmapFactory.decodeStream(
                    application.contentResolver.openInputStream(selectedImageUri)
                )
                binding.ivProductImg.setImageBitmap(selectedImageBitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


        // set Text to textview
        binding.tvProductName.text = products.productName
        binding.tvProductPrice.text = products.regularPrice.toString()
        binding.tvProductSalePrice.text = products.salePrice.toString()
        binding.tvProductDescription.text = products.description

    }
}