package com.example.fragment.password

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords_table")
data class PassItem(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "login") var login: String,
    @ColumnInfo(name = "password") var password: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 1
) {
    fun encrypt(): String {
        // Реализуйте логику шифрования здесь
        return password // Это заглушка. Замените на реальное шифрование
    }
    fun decrypt(): String {
        // Реализуйте логику шифрования здесь
        return password // Это заглушка. Замените на реальное шифрование
    }
}
