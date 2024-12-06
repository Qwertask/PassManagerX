package com.example.fragment

import android.app.Application
import com.example.fragment.database.PassItemDatabase

class PasswordManagerApplication:Application() {
    private val database by lazy { PassItemDatabase.getDatabase(this) }
    val repository by lazy { PassItemRepository(database.passItemDao()) }
}