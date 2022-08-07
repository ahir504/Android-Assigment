package com.example.androidassigment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassigment.R
import com.example.androidassigment.model.ProductModel

class ProductListAdapter(private  val products: ProductModel) : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tv_productListNames)
        val cvClick: CardView = itemView.findViewById(R.id.cv_click)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.productName.text= products.products[position].name
        holder.cvClick.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return products.products.size
    }
}