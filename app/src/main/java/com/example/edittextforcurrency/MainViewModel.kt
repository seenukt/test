package com.example.edittextforcurrency

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.edittextforcurrency.repo.PagingRepo
import com.example.edittextforcurrency.test.ExamplePagingSource
import org.koin.core.component.KoinComponent

class MainViewModel constructor(private val repo:PagingRepo,private  val  coroutinesManager: CoroutinesManager,private val apiService: ApiInterFace) :ViewModel(),KoinComponent {

//    private val _data = MutableLiveData<String>()
//    val data: LiveData<String> get() = _data
//
//
//    fun pagingData(pageNumber: Int, size: Int) = coroutinesManager.ioScope.launch {
//        val response = repo.getData(pageNumber, size)
//        if (response.isSuccessful) {
//            _data.postValue(response.body()?.data?.get(0)?.airline?.get(0)?.name)
//        }
//    }

    val listData = Pager(PagingConfig(pageSize = 20)) {
        ExamplePagingSource(apiService)
    }.liveData.cachedIn(viewModelScope)
}

