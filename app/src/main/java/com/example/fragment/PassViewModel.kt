package com.example.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fragment.password.PassItem
import kotlinx.coroutines.launch

class PassViewModel(private val repository: PassItemRepository):ViewModel() {
    var passItems: LiveData<List<PassItem>> = repository.allPasswords.asLiveData()

    fun addPassItem(newPass: PassItem) = viewModelScope.launch {
        repository.insertPassItem(newPass)
    }

    fun updatePassItem(passItem: PassItem) = viewModelScope.launch {
        repository.updatePassItem(passItem)
    }

}

class PassItemModelFactory(private val repository: PassItemRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom((PassViewModel::class.java)))
            return PassViewModel(repository) as T
        throw IllegalArgumentException("Неизвестный VW класс")
    }
}