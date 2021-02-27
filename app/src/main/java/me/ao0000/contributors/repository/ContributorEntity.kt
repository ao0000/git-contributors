package me.ao0000.contributors.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import me.ao0000.contributors.model.Contributor

@Entity(tableName = "contributor_table")
data class ContributorEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "contributor_id")
    val id: Long,
    @Json(name = "login") val name: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "url") val usersUrl: String,
    val contributions: Int
) {
    fun toModel() = Contributor(
        name,
        avatarUrl,
        usersUrl,
        contributions
    )
}