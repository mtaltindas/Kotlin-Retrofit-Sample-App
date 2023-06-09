package com.altindas.exam2.model

data class AddToCartRequest(
    val userId: Int,
    val products: List<AddToProduct>
)

data class AddToProduct(
    val id: Int,
    val quantity: Int
)
