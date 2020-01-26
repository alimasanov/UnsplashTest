package com.alimasanov.unsplash.model.pojo

import java.io.Serializable

data class Photo(
    var color: String? = null,
    var created_at: String? = null,
    var description: String? = null,
    var downloads: Int? = null,
    var height: Int? = null,
    var id: String? = null,
    var liked_by_user: Boolean? = null,
    var likes: Int? = null,
    var updated_at: String? = null,
    var width: Int? = null,
    var urls: Urls = Urls()
): Serializable