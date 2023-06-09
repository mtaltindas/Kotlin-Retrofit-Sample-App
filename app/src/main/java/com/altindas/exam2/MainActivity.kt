package com.altindas.exam2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import com.altindas.exam2.adapter.ProductsAdapter
import com.altindas.exam2.config.ApiClient
import com.altindas.exam2.models.MarketProducts
import com.altindas.exam2.models.Product
import com.altindas.exam2.service.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var product_service: ProductService
    lateinit var customAdapter: ProductsAdapter

    lateinit var m_showCart: Button
    lateinit var m_productsList: ListView

    var List_Product = mutableListOf<Product>()
    var Common = Commons()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        m_showCart = findViewById(R.id.m_showCart)
        m_productsList = findViewById(R.id.productsList)
        customAdapter = ProductsAdapter(this, List_Product)
        m_productsList.adapter = customAdapter

        product_service = ApiClient.getClient().create(ProductService::class.java)
        product_service.products().enqueue(object : Callback<MarketProducts> {
            override fun onResponse(
                call: Call<MarketProducts>,
                response: Response<MarketProducts>
            ) {
                Log.d("products", response.body().toString())

                for (product in response.body()!!.products) {
                    List_Product.add(product)
                    m_productsList.adapter = ProductsAdapter(this@MainActivity, List_Product)
                    customAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MarketProducts>, t: Throwable) {
                Log.e("eroor", "server error")
            }

        })

        m_productsList.setOnItemClickListener { _, _, position, _ ->
            selectedProduct = List_Product[position]
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }
        m_showCart.setOnClickListener {
            Common.api_getCart(product_service, this)
        }
    }

    companion object {
        lateinit var selectedProduct: Product

        //Locally stored Total price of cart
        var cartTotal: Int = 0
    }
}
