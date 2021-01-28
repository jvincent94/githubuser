package to.tawk.githubuser.ui.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import to.tawk.githubuser.data.repository.UserRepository

/**
 * ViewModel class to present data to our views
 */
class UsersViewModel @ViewModelInject constructor(
    private val repository: UserRepository
): ViewModel() {
    //get users
    val users = repository.getUsers()
}