package com.srk.nytimes.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srk.nytimes.data.remote.model.MostPopular
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MostPopularDetailsViewModel @Inject constructor() : ViewModel() {

    val _mostPopular: MutableLiveData<MostPopular> = MutableLiveData<MostPopular>()
    val mostPopular: LiveData<MostPopular> = _mostPopular

}