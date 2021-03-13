package me.ao0000.contributors.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wada811.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import me.ao0000.contributors.databinding.GalleryFragmentBinding

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val binding: GalleryFragmentBinding by viewBinding()

    private val viewModel by viewModels<GalleryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.fetch()
        setRecyclerAdapter()
    }

    private fun setRecyclerAdapter() {
        val adapter = GalleryRecyclerAdapter(::itemOnClick)
        binding.contributorGalleryRecycler.adapter = adapter
        viewModel.collection.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    private fun itemOnClick(userName: String) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailFragment(userName)
        findNavController().navigate(action)
    }

}