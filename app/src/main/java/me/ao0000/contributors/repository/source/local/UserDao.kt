package me.ao0000.contributors.repository.source.local

import androidx.room.*
import me.ao0000.contributors.repository.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE user_name = :userName")
    suspend fun getUser(userName: String): UserEntity

}