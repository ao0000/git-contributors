package me.ao0000.contributors.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import me.ao0000.contributors.model.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val id: Long,
    @Json(name = "login") val loginName: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    @Json(name = "public_repos") val publicRepos: Int,
    val followers: Int,
    val following: Int
) {
    fun toModel() = User(
        loginName,
        avatarUrl,
        name ?: "",
        company ?: "",
        location ?: "",
        email ?: "",
        bio ?: "",
        publicRepos,
        followers,
        following
    )
}