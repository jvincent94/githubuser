package to.tawk.githubuser.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import to.tawk.githubuser.data.entities.Users
/**
 * Data Access Object (DAO) operations
 */
@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAllUsers() : LiveData<List<Users>>

    @Query("SELECT * FROM users WHERE id = :username")
    fun getUser(username: String): LiveData<Users>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Users>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: Users)
}