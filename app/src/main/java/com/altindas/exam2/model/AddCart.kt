package com.altindas.exam2.model

data class AddCart(
    val discountedTotal: Int,
    val id: Int,
    val products: List<AddCartProduct>,
    val total: Int,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)

data class AddCartProduct(
    val discountPercentage: Double,
    val discountedPrice: Int,
    val id: Int,
    val price: Int,
    val quantity: Int,
    val title: String,
    val total: Int
)