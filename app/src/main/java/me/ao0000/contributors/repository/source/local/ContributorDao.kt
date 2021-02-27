package me.ao0000.contributors.repository.source.local

import androidx.room.*
import me.ao0000.contributors.repository.ContributorEntity

@Dao
interface ContributorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContributors(vararg contributors: ContributorEntity)

    @Query("SELECT * FROM contributor_table ORDER BY contributor_id ASC")
    suspend fun getContributors(): List<ContributorEntity>

    @Update
    suspend fun updateContributors(vararg contributors: ContributorEntity)

}