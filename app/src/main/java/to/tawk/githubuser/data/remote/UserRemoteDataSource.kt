package to.tawk.githubuser.data.remote

import javax.inject.Inject

/**
 * class used for api calls. We need "suspend" modifier
 * because the functions will execute in coroutine
 */

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
): BaseDataSource() {
    suspend fun getUsers() = getResult { userService.getAllUsers() }
    suspend fun getUser(username: String) = getResult { userService.getUser(username) }
}