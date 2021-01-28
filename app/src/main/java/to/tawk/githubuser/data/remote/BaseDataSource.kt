package to.tawk.githubuser.data.remote

import retrofit2.Response
import timber.log.Timber
import to.tawk.githubuser.utils.Resource

/**
 *  Class for catching result and error status in Retrofit
 */

abstract class BaseDataSource {
    //gets result from api response
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    // throws error message when unsuccessful retrieval of data from api
    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error("Network call has failed for a following reason: $message")
    }
}