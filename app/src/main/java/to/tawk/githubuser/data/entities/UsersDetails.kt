package to.tawk.githubuser.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * entity table "user_details" for saving data from network call
 * saves user details
 */

@Entity(tableName = "user_details")
data class UserDetails(
    val login: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val node_id: String? = null,
    val avatar_url: String? = null,
    val gravatar_id: String? = null,
    val url: String? = null,
    val html_url: String? = null,
    val followers_url: String? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val organizations_url: String? = null,
    val repos_url: String? = null,
    val events_url: String? = null,
    val received_events_url: String? = null,
    val type: String? = null,
    val site_admin: Boolean? = null,
    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val hireable: String? = null,
    val bio: String? = null,
    val twitter_username: String? = null,
    val public_repos: Int? = null,
    val public_gists: Int? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val hasNotes: Boolean? = null,
    val notes: String? = null
) {
}