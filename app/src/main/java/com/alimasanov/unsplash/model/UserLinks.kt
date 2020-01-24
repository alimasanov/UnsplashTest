package com.alimasanov.unsplash.model

import java.io.Serializable

data class UserLinks(
    val html: String? = null,
    val likes: String? = null,
    val photos: String? = null,
    val portfolio: String? = null,
    val self: String? = null
): Serializable