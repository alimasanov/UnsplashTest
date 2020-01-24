package com.alimasanov.unsplash.model

import java.io.Serializable

data class Photo(
    val color: String? = null,
    val created_at: String? = null,
    val description: String? = null,
    val downloads: Int? = null,
    val height: Int? = null,
    val id: String? = null,
    val liked_by_user: Boolean? = null,
    val likes: Int? = null,
    val updated_at: String? = null,
    val width: Int? = null,
    val exif: Exif? = null,
    val location: Location? = null,
    val urls: Urls? = null,
    val links: Links? = null,
    val user: User? = null
): Serializable