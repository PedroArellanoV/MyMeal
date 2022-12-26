package com.example.mymealsproyect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.mymealsproyect.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @androidx.room.Query("SELECT * FROM user_table")
    suspend fun getUser(): UserEntity
}