package me.ao0000.contributors.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ao0000.contributors.BuildConfig
import me.ao0000.contributors.model.Contributor
import me.ao0000.contributors.repository.remote.ContributorService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository {

    private val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val service = Retrofit
        .Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ContributorService::class.java)

    suspend fun getContributorList(): List<Contributor> {
        return withContext(Dispatchers.IO) {
            service.getContributorList().map {
                it.toModel()
            }
        }
    }

}