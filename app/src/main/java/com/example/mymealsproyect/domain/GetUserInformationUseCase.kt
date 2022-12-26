package com.example.mymealsproyect.domain

import com.example.mymealsproyect.data.UserRepository
import com.example.mymealsproyect.domain.model.UiUserInformation
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): UiUserInformation = repository.getUserInformation()
}