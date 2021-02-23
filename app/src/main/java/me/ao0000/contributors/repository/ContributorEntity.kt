package me.ao0000.contributors.repository

import com.squareup.moshi.Json
import me.ao0000.contributors.model.Contributor

data class ContributorEntity(
    @Json(name = "login") val name: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "url") val usersUrl: String,
    val contributions: Int
) {
    fun toModel() = Contributor(
        name,
        avatarUrl,
        usersUrl,
        contributions
    )
}