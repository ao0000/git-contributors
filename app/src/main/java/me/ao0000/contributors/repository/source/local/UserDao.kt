package me.ao0000.contributors.repository.source.local

import androidx.room.*
import me.ao0000.contributors.repository.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg user: UserEntity)

    @Query("SELECT * FROM user_table ORDER BY user_id ASC")
    suspend fun getUsers(): List<UserEntity>

    @Update
    suspend fun updateUsers(vararg users: UserEntity)

}