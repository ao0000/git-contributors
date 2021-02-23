package me.ao0000.contributors.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.ao0000.contributors.model.Contributor
import me.ao0000.contributors.repository.Repository

class GalleryViewModel(private val repository: Repository) : ViewModel() {

    private val _collection = MutableLiveData<List<Contributor>>()

    val collection: LiveData<List<Contributor>>
        get() = _collection

    fun fetch() {
        viewModelScope.launch {
            _collection.value = repository.getContributorList()
        }
    }

}