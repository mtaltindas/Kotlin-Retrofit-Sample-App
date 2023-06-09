package com.altindas.exam2.service

import com.altindas.exam2.model.AddCart
import com.altindas.exam2.model.AddToCartRequest
import com.altindas.exam2.model.UserCart
import com.altindas.exam2.models.MarketProducts
import com.altindas.exam2.models.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductService {
    //ALLPRODUCTS
    @GET("products")
    fun products() : Call<MarketProducts>
    //SINGLEPRODUCT
    @GET("products/{id}")
    fun singleProduct( @Path("id") id: Int ) : Call<Product>
    //USERCART
    @GET("carts/user/{id}")
    fun getCart( @Path("id") id: Int ) : Call<UserCart>
    //ADD2CART
    @POST("carts/add")
    fun addCart( @Body body: AddToCartRequest) : Call<AddCart>
}