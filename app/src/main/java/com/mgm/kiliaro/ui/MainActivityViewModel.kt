package com.mgm.kiliaro.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.kiliaro.data.remote.Repository
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.generals.wrappers.SingleLiveEvent
import com.mgm.kiliaro.networking.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _sharedMedia: MutableLiveData<ArrayList<ShareMediaResponse>> = SingleLiveEvent()

    val sharedMedia: LiveData<ArrayList<ShareMediaResponse>> = _sharedMedia

    fun getSharedMedia(){
        viewModelScope.launch {
            when(val res = repository.getSharedMedia()){
                is NetworkResponse.Success -> {_sharedMedia.postValue(res.rawResponse)}
                is NetworkResponse.GenericError -> {}
                is NetworkResponse.NetworkError -> {

                }
            }
        }
    }
}