package me.ao0000.contributors.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import me.ao0000.contributors.BuildConfig
import me.ao0000.contributors.repository.RepositoryImpl
import me.ao0000.contributors.repository.source.local.GitDatabase
import me.ao0000.contributors.repository.source.remote.Service
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val contentType: MediaType = "application/json".toMediaType()
        val format = Json { ignoreUnknownKeys = true }
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(format.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(service: Service, database: GitDatabase): RepositoryImpl {
        return RepositoryImpl(service, database)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GitDatabase {
        return Room.databaseBuilder(
            context,
            GitDatabase::class.java,
            "database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}