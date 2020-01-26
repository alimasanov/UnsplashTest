package com.alimasanov.unsplash.model.pojo

import java.io.Serializable

data class Urls(
    var full: String? = null,
    var raw: String? = null,
    var regular: String? = null,
    var small: String? = null,
    var thumb: String? = null
): Serializable