package com.example.mymealsproyect.data

import com.example.mymealsproyect.data.local.dao.UserDao
import com.example.mymealsproyect.data.local.entities.toDatabase
import com.example.mymealsproyect.domain.model.UiUserInformation
import com.example.mymealsproyect.domain.model.toDomain
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun getUserInformation(): UiUserInformation{
        val response = userDao.getUser()
        return response.toDomain()
    }

    suspend fun insertUserInformation(user: UiUserInformation){
        userDao.insertUser(user.toDatabase())
    }

}