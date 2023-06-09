package com.altindas.exam2.model

data class UserCart(
    val carts: List<CartX>,
    val limit: Int,
    val skip: Int,
    val total: Int
)

data class CartX(
    val discountedTotal: Int,
    val id: Int,
    val products: List<ProductX>,
    val total: Int,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)

data class ProductX(
    val id: Int,
    val title: String,
    val price: Int,
    val quantity: Int,
    val total: Int,
    val discountPercentage: Double,
    val discountedPrice: Int
)