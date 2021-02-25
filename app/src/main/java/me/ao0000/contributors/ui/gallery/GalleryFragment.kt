package me.ao0000.contributors.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.ao0000.contributors.databinding.GalleryFragmentBinding

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private lateinit var binding: GalleryFragmentBinding

    private val viewModel by viewModels<GalleryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            GalleryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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