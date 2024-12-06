package com.example.fragment.password

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDAO {
    @Query("SELECT * FROM passwords_table WHERE id >= 1 ORDER BY id ASC")
    fun allPasswords(): Flow<List<PassItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPassword(passItem: PassItem)

    @Update
    fun updatePassword(passItem: PassItem)

    @Delete
    fun deletePassword(passItem: PassItem)

    @Query("DELETE FROM passwords_table")
    fun deleteAllPasswords(): Int
    /*
    *
    *
    @Query("DELETE FROM passwords_table")
    suspend fun deleteAllPasswords(): Int*/
}
