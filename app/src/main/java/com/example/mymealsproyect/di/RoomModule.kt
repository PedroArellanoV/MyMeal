package com.example.mymealsproyect.di

import android.content.Context
import androidx.room.Room
import com.example.mymealsproyect.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val USER_DATABASE = "user_database"

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDatabase::class.java, USER_DATABASE).build()

    @Singleton
    @Provides
    fun provideUserDao(db: UserDatabase) = db.getUserDao()
}