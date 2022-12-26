package com.example.mymealsproyect.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymealsproyect.data.local.dao.UserDao
import com.example.mymealsproyect.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}