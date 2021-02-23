package me.ao0000.contributors.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ao0000.contributors.model.Contributor
import me.ao0000.contributors.model.User
import me.ao0000.contributors.repository.source.remote.Service
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: Service) : Repository {

    override suspend fun getContributorList(): List<Contributor> {
        return withContext(Dispatchers.IO) {
            service.getContributorList().map {
                it.toModel()
            }
        }
    }

    override suspend fun getUser(userName: String): User {
        return withContext(Dispatchers.IO) {
            service.getUser(userName).toModel()
        }
    }

}

interface Repository {

    suspend fun getContributorList(): List<Contributor>

    suspend fun getUser(userName: String): User

}