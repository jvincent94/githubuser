package to.tawk.githubuser.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import to.tawk.githubuser.data.entities.UserDetails
import to.tawk.githubuser.data.entities.Users

/**
 * Data Access Object (DAO) operations
 */
@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM user_details WHERE login = :username")
    fun getUser(username: String): LiveData<UserDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(userDetails: UserDetails)

    @Query("UPDATE user_details SET notes = :notes, hasNotes = :gotNotes WHERE login = :username ")
    suspend fun updateNotes(username: String, notes: String, gotNotes: Boolean)
}