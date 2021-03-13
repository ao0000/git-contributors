package me.ao0000.contributors.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.ao0000.contributors.BuildConfig
import me.ao0000.contributors.repository.RepositoryImpl
import me.ao0000.contributors.repository.source.local.GitDatabase
import me.ao0000.contributors.repository.source.remote.Service
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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