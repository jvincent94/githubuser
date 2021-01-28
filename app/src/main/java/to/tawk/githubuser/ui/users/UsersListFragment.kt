package to.tawk.githubuser.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import to.tawk.githubuser.R
import to.tawk.githubuser.utils.Resource
import to.tawk.githubuser.utils.autoCleared
import to.tawk.githubuser.databinding.FragmentUsersBinding

@AndroidEntryPoint
class UsersListFragment: Fragment(), UsersAdapter.UserItemListener {
    private var binding: FragmentUsersBinding by autoCleared()
    private val viewModel: UsersViewModel by viewModels()
    private lateinit var adapter: UsersAdapter

    // inflate views
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    // set up recyclerview
    private fun setupRecyclerView() {
        adapter = UsersAdapter(this)
        binding.recyclerviewUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewUsers.adapter = adapter
    }

    // setup observers
    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    // handle click listener from adapter
    override fun onClickedUser(username: String) {
        //initiate nave controller, pass username in bundle to UserDetailsFragment
        findNavController().navigate(
            R.id.action_usersFragment_to_userDetailsFragment,
            bundleOf("username" to username)
        )
    }
}