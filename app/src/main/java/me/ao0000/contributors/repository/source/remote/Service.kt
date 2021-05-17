package me.ao0000.contributors.repository.source.remote

import me.ao0000.contributors.model.ContributorEntity
import me.ao0000.contributors.model.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("repos/googlesamples/android-architecture-components/contributors")
    suspend fun getContributors(): List<ContributorEntity>

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): UserEntity

}