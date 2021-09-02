package com.srk.nytimes.ui.mostpopular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srk.nytimes.BuildConfig
import com.srk.nytimes.data.remote.model.MostPopular
import com.srk.nytimes.data.remote.model.MostPopularResponse
import com.srk.nytimes.data.remote.model.param.MostPopularParam
import com.srk.nytimes.data.repository.BaseRepository
import com.srk.nytimes.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularViewModel @Inject constructor(
    private val repository: BaseRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _mostPopularList: MutableLiveData<List<MostPopular>> = MutableLiveData()
    val mostPopularList: LiveData<List<MostPopular>> = _mostPopularList

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message: MutableLiveData<Event<String>> = MutableLiveData()
    val message: LiveData<Event<String>> = _message

    init {
        invokeMostPopular()
    }

    fun invokeMostPopular() {

        val param = MostPopularParam(
            section = Section.ALL_SECTIONS.value,
            period = Period.DAY.value,
            apiKey = BuildConfig.API_KEY
        )

        if (networkHelper.isNetworkConnected()) {
            getMostPopular(param)
        }
        else
            _message.postValue(Event("No internet connectivity, try again later"))
    }

    private fun getMostPopular(param: MostPopularParam) = viewModelScope.launch {

        val mostPopularCall = repository.getMostPopular(param)

        mostPopularCall.observeForever {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    _isLoading.postValue(false)

                    it?.data?.let { mpResponse ->
                        mpResponse.results?.let { mpList ->
                            _mostPopularList.postValue(mpList)
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    _isLoading.postValue(false)
                    it.message?.let { msg ->
                        _message.postValue(Event(msg))
                    }
                }
                Resource.Status.LOADING -> {
                    _isLoading.postValue(true)
                }
            }
        }
    }
}