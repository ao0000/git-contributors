package me.ao0000.contributors.model

data class User(
    val loginName: String,
    val avatarUrl: String,
    val name: String,
    val company: String,
    val location: String,
    val email: String,
    val bio: String,
    val publicRepos: Int,
    val followers: Int,
    val following: Int
)