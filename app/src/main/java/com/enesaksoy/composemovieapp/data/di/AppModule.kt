package com.enesaksoy.composemovieapp.data.di

import com.enesaksoy.composemovieapp.data.remote.MoviesApi
import com.enesaksoy.composemovieapp.data.repository.MovieRepositoryImpl
import com.enesaksoy.composemovieapp.domain.repository.MovieRepository
import com.enesaksoy.composemovieapp.util.Constans.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun injectApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun injectRepo(api: MoviesApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}