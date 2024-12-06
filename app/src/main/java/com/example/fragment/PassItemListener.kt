package com.example.fragment

import com.example.fragment.password.PassItem

interface PassItemListener {
    fun editPassItem(passItem: PassItem)
    fun deletePassItem(passItem: PassItem)
}