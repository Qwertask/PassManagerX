package com.example.fragment

import androidx.annotation.WorkerThread
import com.example.fragment.password.PassItem
import com.example.fragment.password.PasswordDAO
import kotlinx.coroutines.flow.Flow

class PassItemRepository(private val passwordDAO: PasswordDAO) {
    val allPasswords: Flow<List<PassItem>> = passwordDAO.allPasswords()

    @WorkerThread
    suspend fun updatePassItem(passItem: PassItem){
        passwordDAO.updatePassword(passItem)
    }

    @WorkerThread
    suspend fun insertPassItem(passItem: PassItem){
        passwordDAO.insertPassword(passItem)
    }

}