package to.tawk.githubuser.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * entity table "users" for saving data from network call
 *
 */

@Entity(tableName = "users")
data class Users(
    val login: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val nodeId: String? = null,
    val avatar_url: String? = null,
    val gravatarId: String? = null,
    val url: String? = null,
    val htmlUrl: String? = null,
    val followersUrl: String? = null,
    val followingUrl: String? = null,
    val gistsUrl: String? = null,
    val starredUrl: String? = null,
    val subscriptionsUrl: String? = null,
    val organizationsUrl: String? = null,
    val reposUrl: String? = null,
    val eventsUrl: String? = null,
    val receivedEventsUrl: String? = null,
    val type: String? = null,
    val siteAdmin: Boolean? = null,
    val hasNotes: Boolean? = null
) {
}