package me.ao0000.contributors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.ao0000.contributors.databinding.GalleryFragmentBinding

class GalleryFragment : Fragment() {

    private lateinit var binding: GalleryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            GalleryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}