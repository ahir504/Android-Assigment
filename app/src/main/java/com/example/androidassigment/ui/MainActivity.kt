package com.example.androidassigment.ui

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.androidassigment.databinding.ActivityMainBinding
import com.example.androidassigment.entity.*
import com.example.androidassigment.model.ColorModel
import com.example.androidassigment.model.ProductModel
import com.example.androidassigment.model.Store
import com.example.androidassigment.util.Constant
import com.example.androidassigment.util.Util
import com.example.androidassigment.viewModel.ProductViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 200
    lateinit var binding : ActivityMainBinding
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        requestPermission()
        checkPermission()

        binding.btShowProduct.setOnClickListener{
            startActivity( Intent(this, ProductsActivity::class.java))
        }

        binding.btCreateProduct.setOnClickListener{
            val intent= Intent(this,UpdateProductActivity::class.java)
            intent.putExtra(Constant.FROM,0)
            startActivity(intent)
        }


        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        val productsFromJSON = Util.getDataFromProductJson(applicationContext, Constant.PRODUCT_FILE_NAME)
        val storesFromJSON = Util.getDataFromStoreJson(applicationContext, Constant.STORE_FILE_NAME)
        val colorsFromJSON = Util.getDataFromColorJson(applicationContext, Constant.COLOR_FILE_NAME)

        mProductViewModel.getAllProduct().observe(this){
            if (it.isEmpty()){
                insertProductDataToDataBase(productsFromJSON,storesFromJSON,colorsFromJSON)
            }
        }

        insertStoreDataToDataBase(storesFromJSON)
        insertColorDataToDataBase(colorsFromJSON)

    }

    private fun insertProductDataToDataBase(products: ProductModel?, store: Store?, colorModel: ColorModel?){
        for (product in products!!.products) {
            val productEntity = Product(
                product.productId,
                product.productName,
                product.description,
                product.regularPrice,
                product.salePrice,
                product.productImage,
            )
            for (i in products.products){
                val productStoreCrossRef =
                    ProductStoreCrossRef(product.productId, store!!.stores.random().storeId)
                val productColorCrossRef =
                    ProductAndColorCrossRef(product.productId, colorModel!!.color.random().colorId)

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

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE)
        val result1 = ContextCompat.checkSelfPermission(applicationContext, WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.isNotEmpty()) {
                val readAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val writeAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (readAccepted && writeAccepted)
                    else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel("You need to allow access to both the permissions"
                            ) { _, _ ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(
                                        arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE),
                                        PERMISSION_REQUEST_CODE
                                    )
                                }
                            }
                            return
                        }
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@MainActivity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

}