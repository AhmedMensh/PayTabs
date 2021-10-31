package com.android.paytabs.models


data class BooksResponseModel(
    val author: String ? = null,
    val available: Boolean ? = null,
    val currentStock: Int? = null,
    val id: Int? = null,
    val isbn: String? = null,
    val name: String? = null,
    var price: Double? = null,
    val type: String? = null
)
