package com.alimasanov.unsplash.model

import java.io.Serializable

data class Location(
    val city: String? = null,
    val country: String? = null,
    val position: Position? = null
): Serializable