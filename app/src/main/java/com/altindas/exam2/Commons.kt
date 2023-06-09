package com.altindas.exam2

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.altindas.exam2.config.ApiClient
import com.altindas.exam2.model.AddCart
import com.altindas.exam2.model.AddToCartRequest
import com.altindas.exam2.model.AddToProduct
import com.altindas.exam2.model.ProductX
import com.altindas.exam2.model.UserCart
import com.altindas.exam2.service.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
    Ortak kullanılan fonksiyonlar için Commons classını oluşturdum.
    api_getCart ->carts/user/{id} response u ile aldığı kullanıcının sepetini alertdialog ile gösteriyor.
    api_addtoCart->carts/add response
    show_cart_alert->alertdialog oluşturmak için kullanılan ortak bir method

 */

class Commons {
    fun api_getCart(product_service:ProductService, context: Activity ){
        product_service.getCart(1).enqueue(object : Callback<UserCart> {
            override fun onResponse(call: Call<UserCart>, response: Response<UserCart>) {
                if(response.isSuccessful){
                    var totalAmount=0
                    var totalPrice=0
                    var cartItems= mutableListOf<ProductX>()
                    val apiResponse = response.body()

                    // Log.d("apiResponse", apiResponse.toString())
                    for (item in apiResponse!!.carts){

                        totalPrice=item.discountedTotal
                        for (item2 in item.products){
                            cartItems.add(item2)
                            totalAmount+=item2.quantity
                        }
                    }
                    show_cart_alert(cartItems,totalPrice,totalAmount,context)
                }
            }
            override fun onFailure(call: Call<UserCart>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun show_cart_alert(cartItems: MutableList<ProductX>, totalPrice: Int, totalAmount: Int, context: Activity){
        var cartString="\n"
        for (item in cartItems ){
            cartString+=item
            cartString+="\n\n"
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Price: ${totalPrice}TL / Total Item Count: ${totalAmount}")
        builder.setMessage("${cartString}")
        builder.setNeutralButton("Close"){dialogInterface , which ->
            Log.d("Close"," ")
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
    fun api_addtoCart(id: Long, context: Activity) {
        var product_service = ApiClient.getClient().create(ProductService::class.java)

        val request = AddToCartRequest(
            userId = 1,
            products = listOf(
                AddToProduct(id = id.toInt(), quantity = 1),
            )
        )
        product_service.addCart(request).enqueue(object : Callback<AddCart> {
            override fun onResponse(call: Call<AddCart>, response: Response<AddCart>) {
                val apiResponse = response.body()
                MainActivity.cartTotal +=apiResponse!!.total
                Toast.makeText(context,"Succesfully Added to your Cart.\nCart Total:${MainActivity.cartTotal}\n", Toast.LENGTH_LONG).show()
            }
            override fun onFailure(call: Call<AddCart>, t: Throwable) {
                Log.e("allUser Error", t.toString())
            }
        })
    }
}