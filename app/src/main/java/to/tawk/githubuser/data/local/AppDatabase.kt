package to.tawk.githubuser.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import to.tawk.githubuser.data.entities.UserDetails
import to.tawk.githubuser.data.entities.Users

/**
 *  Room database class, for caching data if internet connection
 *  is not present
 */

@Database(entities = [Users::class, UserDetails::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    //instantiate Dao entities
    abstract fun userDao(): UsersDao
    abstract fun userDetailsDao(): UserDetailsDao

    companion object {
        //AppDatabase instance
        @Volatile private var instance: AppDatabase? = null

        //public function for database initialization
        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        //build database config
        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "github")
                .fallbackToDestructiveMigration()
                .build()
    }
}