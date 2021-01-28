package to.tawk.githubuser.data.remote


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import to.tawk.githubuser.data.entities.UserDetails
import to.tawk.githubuser.data.entities.Users

/**
 * interface class used to communicate with API using
 * HTTP operations
 */

interface UserService {

    @GET("users")
    suspend fun getAllUsers() : Response<List<Users>>

    @GET("users/{username}")
    suspend fun getUser( @Path("username") username: String ): Response<UserDetails>

}