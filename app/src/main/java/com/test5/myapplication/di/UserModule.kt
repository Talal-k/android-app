package com.test5.myapplication.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test5.myapplication.config.Base_URL
import com.test5.myapplication.data.Repository.Repository
import com.test5.myapplication.data.db.UserDao
import com.test5.myapplication.data.db.UserDatabase
import com.test5.myapplication.data.net.UserApi
import com.test5.myapplication.data.net.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class UserModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(Base_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
    @Provides
    fun provideService(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(Userapi: UserApi) = UserRemoteDataSource(Userapi)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = UserDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDao(db: UserDatabase) = db.UserDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: UserRemoteDataSource, localDataSource: UserDao
    ) = Repository(localDataSource,remoteDataSource)
}