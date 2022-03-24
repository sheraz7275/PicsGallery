package com.example.picgallery.di

import com.example.picgallery.dataSource.ImageDataSource
import com.example.picgallery.dataSource.remote.RemoteDataSource
import com.example.picgallery.dataSource.remote.RestService
import com.example.picgallery.repository.DefaultRepository
import com.example.picgallery.repository.ImageRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {


    private const val BaseURL = "https://api.flickr.com/"

    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BaseURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RestService =
        retrofit.create(RestService::class.java)

    @Singleton
    @Provides
    fun provideDataSource(restApi: RestService): ImageDataSource = RemoteDataSource(restApi)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): ImageRepository =
        DefaultRepository(remoteDataSource)


}