package com.altindas.exam2.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.altindas.exam2.Commons
import com.altindas.exam2.R
import com.altindas.exam2.models.Product
import com.bumptech.glide.Glide


class ProductsAdapter (private val context: Activity, private val List_Product: List<Product>) :
    ArrayAdapter<Product>(context,R.layout.custom_list_product, List_Product ){

    var Common= Commons()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.custom_list_product, null, true)


        val productTitle = rootView.findViewById<TextView>(R.id.p_title)
        val productDes = rootView.findViewById<TextView>(R.id.p_description)
        val productPrice = rootView.findViewById<TextView>(R.id.p_price)
        val cart_button = rootView.findViewById<Button>(R.id.cart_button)
        val imageView = rootView.findViewById<ImageView>(R.id.p_img)


        val product = List_Product[position]


        productTitle.text=product.title
        productDes.text=product.description
        productPrice.text="${product.price.toString()}"+ "TL"

        Glide.with(context)
            .load(product.thumbnail)
            .override(200, 100)
            .centerCrop()
            .into(imageView)


        cart_button.setOnClickListener {
            Common.api_addtoCart(product.id,context)
        }

        return rootView
    }



}