package com.altindas.exam2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.altindas.exam2.config.ApiClient
import com.altindas.exam2.service.ProductService

import com.bumptech.glide.Glide

class ProductActivity : AppCompatActivity() {

    var Common=Commons()
    lateinit var product_title: TextView
    lateinit var product_description: TextView
    lateinit var product_rating: TextView
    lateinit var product_price: TextView
    lateinit var product_image: ImageView
    lateinit var addCart: Button
    lateinit var showCart: Button

    lateinit var product_service: ProductService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)


        product_title=findViewById(R.id.product_title)
        product_description=findViewById(R.id.product_description)
        product_rating=findViewById(R.id.product_rating)
        product_price=findViewById(R.id.product_price)
        product_image=findViewById(R.id.product_image)

        addCart=findViewById(R.id.addCart)              //button
        showCart=findViewById(R.id.showCart)            //button

        product_title.text=MainActivity.selectedProduct.title
        product_description.text=MainActivity.selectedProduct.description
        product_rating.text="User Reviews :${MainActivity.selectedProduct.rating}"
        product_price.text="${MainActivity.selectedProduct.price.toString()}"+"TL"

        Glide.with(this)
            .load(MainActivity.selectedProduct.thumbnail)
            .override(400, 300)
            .centerCrop()
            .into(product_image)

        product_service = ApiClient.getClient().create(ProductService::class.java)

        addCart.setOnClickListener {
            Common.api_addtoCart(MainActivity.selectedProduct.id,this)
        }

        showCart.setOnClickListener {
            Common.api_getCart(product_service,this)
        }

    }

}