package com.example.fragment.password

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDAO {
    @Query("DELETE FROM passwords_table")
    suspend fun deleteAllPaswords()

    @Query("SELECT * FROM passwords_table WHERE id >= 1 ORDER BY id ASC")
    fun allPasswords(): Flow<List<PassItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassword(passItem: PassItem)

    @Update
    suspend fun updatePassword(passItem: PassItem)

    @Delete
    suspend fun deletePassword(passItem: PassItem)
}
