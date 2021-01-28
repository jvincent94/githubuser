package to.tawk.githubuser.data.repository

import to.tawk.githubuser.data.local.UserDetailsDao
import to.tawk.githubuser.data.local.UsersDao
import to.tawk.githubuser.data.remote.UserRemoteDataSource
import to.tawk.githubuser.utils.performGetOperation
import javax.inject.Inject

/**
 * Repository class used for processing data to
 * cache and display data from API
 */

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UsersDao,
    private val localUserDetailsSource: UserDetailsDao
) {
    fun getUser(username: String) = performGetOperation(
            databaseQuery = { localUserDetailsSource.getUser(username) },
            networkCall ={ remoteDataSource.getUser(username) },
            saveCallResult = { localUserDetailsSource.insertDetails(it) }
    )

    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = {  localDataSource.insertAll(it) }
    )
}