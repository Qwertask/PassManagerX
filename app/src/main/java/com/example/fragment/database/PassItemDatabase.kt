package com.example.fragment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fragment.password.PassItem
import com.example.fragment.password.PasswordDAO

@Database(entities = [PassItem::class], version = 1, exportSchema = false)
abstract class PassItemDatabase:RoomDatabase() {

    abstract fun passItemDao():PasswordDAO

    companion object{
        @Volatile
        private var INSTANCE:PassItemDatabase? = null
        fun getDatabase(context: Context):PassItemDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PassItemDatabase::class.java,
                    "passwords_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}