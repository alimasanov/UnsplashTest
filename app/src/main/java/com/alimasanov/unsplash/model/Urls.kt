package com.alimasanov.unsplash.model

import java.io.Serializable

data class Urls(
    val full: String? = null,
    val raw: String? = null,
    val regular: String? = null,
    val small: String? = null,
    val thumb: String? = null
): Serializable