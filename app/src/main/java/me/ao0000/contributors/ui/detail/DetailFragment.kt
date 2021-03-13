package me.ao0000.contributors.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import me.ao0000.contributors.databinding.DetailFragmentBinding

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observeUser()
        viewModel.fetchUser(args.userName)
    }

    private fun observeUser() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.apply {
                avatarImage.load(it.avatarUrl) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                nameText.text = it.name
                loginNameText.text = it.loginName
                followingText.text = "following : " + it.following
                followerText.text = "follower : " + it.followers
                publicReposText.text = "public repos : " + it.publicRepos
                emailText.text = "email : " + it.email
                locationText.text = "location : " + it.location
                companyText.text = "company : " + it.company
                bioText.text = it.bio
            }
        })
    }
}