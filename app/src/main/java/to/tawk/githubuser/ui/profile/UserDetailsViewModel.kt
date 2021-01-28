package to.tawk.githubuser.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import to.tawk.githubuser.data.entities.UserDetails
import to.tawk.githubuser.data.repository.UserRepository
import to.tawk.githubuser.utils.Resource

/**
 * ViewModel class to present data to our views
 */
class UserDetailsViewModel @ViewModelInject constructor(

        private val repository: UserRepository
): ViewModel() {
    private val _username = MutableLiveData<String>()

    private val _userDetails = _username.switchMap { username ->
        repository.getUser(username)
    }

    val userDetails: LiveData<Resource<UserDetails>> = _userDetails

    fun start(uName: String) {
        _username.value = uName
    }

}