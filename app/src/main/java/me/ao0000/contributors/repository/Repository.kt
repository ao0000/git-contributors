package me.ao0000.contributors.repository

import me.ao0000.contributors.model.Contributor
import me.ao0000.contributors.model.User


interface Repository {

    suspend fun getContributors(): List<Contributor>

    suspend fun getUser(userName: String): User
}