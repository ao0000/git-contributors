package me.ao0000.contributors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.ao0000.contributors.repository.Repository
import me.ao0000.contributors.ui.GalleryViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(
        modelClass: Class<T>
    ) = GalleryViewModel(repository) as T
}