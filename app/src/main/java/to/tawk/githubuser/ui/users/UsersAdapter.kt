package to.tawk.githubuser.ui.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import to.tawk.githubuser.data.entities.Users
import to.tawk.githubuser.databinding.ItemUserBinding

/**
 * Adapter class responsible for displaying users
 * in list
 */
class UsersAdapter(private val listener: UserItemListener): RecyclerView.Adapter<UserViewHolder>(){
    // inferface for onClick listener
    interface UserItemListener {
        fun onClickedUser(username: String)
    }
    // array list container for User object
    private val items = ArrayList<Users>()

    // set objects inside arraylist
    fun setItems(items: ArrayList<Users>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    // inflate UI
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    // get count of objects inside arraylist
    override fun getItemCount(): Int = items.size

    // bind views and arraylist
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(items[position])
}

// class to responsible for displaying data in ui
class UserViewHolder(private val itemBinding: ItemUserBinding, private val listener: UsersAdapter.UserItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var users: Users

    init {
        itemBinding.root.setOnClickListener(this)
    }

    //binds ui components
    @SuppressLint("SetTextI18n")
    fun bind(item: Users) {
        this.users = item
        itemBinding.tvName.text = item.login
        itemBinding.tvDetails.text = item.type
        Glide.with(itemBinding.root)
            .load(item.avatar_url)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    //handles click listener per item
    override fun onClick(v: View?) {
        listener.onClickedUser(users.login.toString())
    }
}