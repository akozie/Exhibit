package com.example.seampay.di

import android.app.Application
import androidx.room.Room
import com.example.seampay.db.ExhibitDatabase
import com.example.seampay.model.ExhibitsLoader
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
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ExhibitsLoader.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideExhibitsLoader(retrofit: Retrofit): ExhibitsLoader =
        retrofit.create(ExhibitsLoader::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : ExhibitDatabase =
        Room.databaseBuilder(app, ExhibitDatabase::class.java, "iphone_database")
            .fallbackToDestructiveMigration()
            .build()
}