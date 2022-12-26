package com.example.mymealsproyect.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.mymealsproyect.domain.model.UiUserInformation

@Entity(tableName = "user_table")
data class UserEntity(
    @ColumnInfo(name = "firstname") val firstname: String,
    @ColumnInfo(name = "lastname") val lastname: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String
)

fun UiUserInformation.toDatabase() = UserEntity(firstname, lastname, username, email)
