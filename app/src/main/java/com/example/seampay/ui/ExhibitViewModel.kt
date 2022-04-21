package com.example.seampay.ui

import androidx.lifecycle.*
import com.example.seampay.db.ExhibitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExhibitViewModel @Inject constructor(
    private val repository: ExhibitRepository
) : ViewModel() {

    val exhibit = repository.getExhibit().asLiveData()

}