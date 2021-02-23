package me.ao0000.contributors.repository.source.remote

import me.ao0000.contributors.repository.ContributorEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface ContributorService {
    @GET("repos/googlesamples/android-architecture-components/contributors")
    suspend fun getContributorList(): List<ContributorEntity>

    @GET("users/ianhanniballake/{user}")
    suspend fun getUser(@Path("user") user: String)
}