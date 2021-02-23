package me.ao0000.contributors.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ao0000.contributors.model.Contributor
import me.ao0000.contributors.repository.remote.ContributorService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: ContributorService) : Repository {

    override suspend fun getContributorList(): List<Contributor> {
        return withContext(Dispatchers.IO) {
            service.getContributorList().map {
                it.toModel()
            }
        }
    }

}

interface Repository {
    suspend fun getContributorList(): List<Contributor>
}