package me.ao0000.contributors.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "user_table")
@Serializable
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val id: Long,

    @SerialName("login")
    @ColumnInfo(name = "user_name")
    val loginName: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    @SerialName("public_repos")
    val publicRepos: Int,
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