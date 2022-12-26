package com.example.mymealsproyect.domain.model

import com.example.mymealsproyect.data.local.entities.UserEntity

data class UiUserInformation(
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String
)

fun UserEntity.toDomain() = UiUserInformation(firstname, lastname, username, email)
