package com.exchange.viewModel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.exchange.model.SearchPics
import com.exchange.repository.DashboardRepo
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.lang.Exception

class DashboardViewModel(private val repo: DashboardRepo, application: Application) :
    AndroidViewModel(application) {

    var mLoading = MutableLiveData<Boolean>()
    var mError = MutableLiveData<Exception>()

    var searchText: ObservableField<String> = ObservableField()

    var mResponse = MutableLiveData<SearchPics>()

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getSearchPics(SearchText: String) {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                val asyncPost = async {
                    repo.fetchPics(SearchText)
                }
                val response = asyncPost.await()

                if (response.isSuccessful) {

                    viewModelScope.launch(Dispatchers.Main) {
                        mLoading.value = false
                        mResponse.value = response.body()
                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        mLoading.value = false
                        mError.value = HttpException(response)
                    }
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    mError.value = e
                    mLoading.value = false

                }
            }
        }
    }

}