package me.ao0000.contributors.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ao0000.contributors.model.Contributor
import me.ao0000.contributors.model.User
import me.ao0000.contributors.repository.source.local.GitDatabase
import me.ao0000.contributors.repository.source.remote.Service
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: Service,
    database: GitDatabase
) : Repository {

    private val contributorDao = database.contributorDao()

    private val userDao = database.userDao()

    override suspend fun getContributors(): List<Contributor> {
        return withContext(Dispatchers.IO) {
            fetchContributors()
            contributorDao.getContributors().map {
                it.toModel()
            }
        }
    }

    override suspend fun getUser(userName: String): User {
        return withContext(Dispatchers.IO) {
            fetchUser(userName)
            userDao.getUser(userName).toModel()
        }
    }

    private suspend fun fetchContributors() {
        val contributors = service.getContributors()
        contributorDao.insertContributors(contributors)
    }

    private suspend fun fetchUser(userName: String) {
        val user = service.getUser(userName)
        userDao.insertUser(user)
    }
}