package com.example.androidassigment.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassigment.R
import com.example.androidassigment.model.ProductModel
import com.example.androidassigment.model.Products
import com.example.androidassigment.model.StoreModel
import com.example.androidassigment.ui.DisplayProductActivity
import com.example.androidassigment.util.Constant

class StoreListAdapter(private val context: Context, private  val store: List<StoreModel>) : RecyclerView.Adapter<StoreListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tv_productListNames)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.productName.text= store[position].storeName


    }

    override fun getItemCount(): Int {
        return store.size
    }
}