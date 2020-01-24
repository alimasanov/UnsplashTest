package com.alimasanov.unsplash.model

import java.io.Serializable

data class User(
    val bio: String? = null,
    val id: String? = null,
    val location: String? = null,
    val name: String? = null,
    val portfolio_url: String? = null,
    val total_collections: Int? = null,
    val total_likes: Int? = null,
    val total_photos: Int? = null,
    val updated_at: String? = null,
    val username: String? = null,
    val links: UserLinks? = null
): Serializable