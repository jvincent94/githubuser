package to.tawk.githubuser.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber
import to.tawk.githubuser.data.entities.UserDetails
import to.tawk.githubuser.data.entities.Users
import to.tawk.githubuser.databinding.FragmentUserDetailsBinding
import to.tawk.githubuser.utils.Resource
import to.tawk.githubuser.utils.autoCleared

/**
 * Fragment class responsible for displaying
 * data and UI transactions
 */

// Inject Hilt to get dependencies
@AndroidEntryPoint
class UserDetailsFragment: Fragment() {
    private var binding: FragmentUserDetailsBinding by autoCleared()
    private val viewModel: UserDetailsViewModel by viewModels()

    //inflate layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //call to look for data where equals to username
        arguments?.getString("username")?.let { viewModel.start(it) }
        // call observers
        setupObservers()
    }

    // function that observes the result from LiveData and updates itself
    private fun setupObservers() {
        viewModel.userDetails.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> bindCharacter(it1) }
                    binding.progressBar.visibility = View.GONE
                    binding.layoutAvatar.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.layoutAvatar.visibility = View.GONE
                }
            }
        })
    }

    // function that binds ui components
    private fun bindCharacter(userDetails: UserDetails) {
        if(userDetails.name != null){
            binding.tvName.text = userDetails.name
        } else {
            binding.tvName.text = "Not Available"
        }

        if(userDetails.company != null){
            binding.tvCompany.text = userDetails.company
        } else {
            binding.tvCompany.text = "Not Available"
        }

        if(userDetails.blog != null){
            binding.tvBlog.text = userDetails.blog
        } else {
            binding.tvBlog.text = "Not Available"
        }

        binding.tvFollowers.text = "Followers: "+ userDetails.followers.toString()
        binding.tvFollowing.text = "Following: "+ userDetails.following.toString()
        Glide.with(binding.root)
            .load(userDetails.avatar_url)
            .transform(CircleCrop())
            .into(binding.image)

        binding.btnSave.setOnClickListener {

        }
    }




}