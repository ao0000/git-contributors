package me.ao0000.contributors.repository.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.ao0000.contributors.repository.ContributorEntity
import me.ao0000.contributors.repository.UserEntity

@Database(
    entities = [ContributorEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GitDatabase : RoomDatabase() {

    abstract fun contributorDao(): ContributorDao

    abstract fun userDao(): UserDao

}
