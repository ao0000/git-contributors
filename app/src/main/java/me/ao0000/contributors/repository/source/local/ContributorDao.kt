package me.ao0000.contributors.repository.source.local

import androidx.room.*
import me.ao0000.contributors.repository.ContributorEntity

@Dao
interface ContributorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContributors(contributors: List<ContributorEntity>)

    @Query("SELECT * FROM contributor_table ORDER BY contributions DESC")
    suspend fun getContributors(): List<ContributorEntity>

}