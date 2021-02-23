package me.ao0000.contributors.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import me.ao0000.contributors.repository.Repository
import me.ao0000.contributors.ViewModelFactory
import me.ao0000.contributors.databinding.GalleryFragmentBinding

class GalleryFragment : Fragment() {

    private lateinit var binding: GalleryFragmentBinding

    private val viewModel by viewModels<GalleryViewModel> { ViewModelFactory(Repository()) }

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

        val adapter = GalleryRecyclerAdapter()

        binding.contributorGalleryRecycler.adapter = adapter

        viewModel.collection.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

    }
}