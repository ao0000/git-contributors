package me.ao0000.contributors.repository.remote

import me.ao0000.contributors.repository.ContributorEntity
import retrofit2.http.GET

interface ContributorService {
    @GET("repos/googlesamples/android-architecture-components/contributors")
    suspend fun getContributorList(): List<ContributorEntity>
}