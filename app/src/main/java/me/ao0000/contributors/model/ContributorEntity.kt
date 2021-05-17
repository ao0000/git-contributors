package me.ao0000.contributors.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "contributor_table")
@Serializable
data class ContributorEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "contributor_id") val id: Long,
    @SerialName("login") val name: String,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("url") val usersUrl: String,
    @ColumnInfo(name = "contributions")
    val contributions: Int
) {
    fun toModel() = Contributor(
        name,
        avatarUrl,
        usersUrl,
        contributions
    )
}