package me.ao0000.contributors.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.ao0000.contributors.BuildConfig
import me.ao0000.contributors.repository.RepositoryImpl
import me.ao0000.contributors.repository.remote.ContributorService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ContributorService {
        return retrofit.create(ContributorService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(service: ContributorService): RepositoryImpl {
        return RepositoryImpl(service)
    }

}