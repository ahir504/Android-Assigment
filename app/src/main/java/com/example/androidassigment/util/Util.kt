package com.example.androidassigment.util

import android.content.Context
import com.example.androidassigment.model.*
import com.google.gson.GsonBuilder
import java.io.IOException
import java.io.InputStream

object Util {
    fun getDataFromProductJson(context: Context?, fileName: String): ProductModel? {
        val jsonString: String
        try {
            val input: InputStream = context!!.assets.open(fileName)
            val size: Int = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            jsonString = String(buffer, Charsets.UTF_8)

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return gson.fromJson(jsonString, ProductModel::class.java)
    }

    fun getDataFromStoreJson(context: Context?, fileName: String): Store? {
        val jsonString: String
        try {
            val input: InputStream = context!!.assets.open(fileName)
            val size: Int = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            jsonString = String(buffer, Charsets.UTF_8)

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return gson.fromJson(jsonString, Store::class.java)
    }

    fun getDataFromColorJson(context: Context?, fileName: String): ColorModel? {
        val jsonString: String
        try {
            val input: InputStream = context!!.assets.open(fileName)
            val size: Int = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            jsonString = String(buffer, Charsets.UTF_8)

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return gson.fromJson(jsonString, ColorModel::class.java)
    }
}