package com.mgm.kiliaro.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.kiliaro.data.remote.Repository
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.networking.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _genericError: MutableLiveData<String> = MutableLiveData()
    val genericError: LiveData<String> = _genericError

    private val _sharedMedia: MutableLiveData<ArrayList<ShareMediaResponse>> = MutableLiveData()
    val sharedMedia: LiveData<ArrayList<ShareMediaResponse>> = _sharedMedia

    fun getSharedMedia() {
        if (repository.getSharedMediaLocal().isNullOrEmpty()) {
            viewModelScope.launch {
                when (val res = repository.getSharedMedia()) {
                    is NetworkResponse.Success -> {
                        _sharedMedia.postValue(res.rawResponse)
                    }
                    is NetworkResponse.GenericError -> {
                        _genericError.postValue(res.error.error)
                    }
                    is NetworkResponse.NetworkError -> {
                        _genericError.postValue("please check your network and try again")
                    }
                }
            }
        }else{
            _sharedMedia.postValue(repository.getSharedMediaLocal())
        }
    }

    fun clearAllSharedPrefs(){
        repository.clearAllSharedPrefs()
    }
}