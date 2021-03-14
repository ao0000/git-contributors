package me.ao0000.contributors.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.wada811.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import me.ao0000.contributors.R
import me.ao0000.contributors.databinding.DetailFragmentBinding

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val binding: DetailFragmentBinding by viewBinding()

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

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
                followingText.text =
                    resources.getString(R.string.following_display_text) + it.following
                followerText.text =
                    resources.getString(R.string.follower_display_text) + it.followers
                publicReposText.text =
                    resources.getString(R.string.public_repos_display_text) + it.publicRepos
                emailText.text = resources.getString(R.string.email_display_text) + it.email
                locationText.text =
                    resources.getString(R.string.location_display_text) + it.location
                companyText.text = resources.getString(R.string.company_display_text) + it.company
                bioText.text = it.bio
            }
        })
    }
}