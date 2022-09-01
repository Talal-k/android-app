package com.test5.myapplication.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.test5.myapplication.data.Repository.Repository
import com.test5.myapplication.data.model.Desc
import com.test5.myapplication.data.model.NetworkResult
import com.test5.myapplication.data.model.SingleUser
import com.test5.myapplication.data.model.User
import kotlinx.coroutines.launch


class DetailViewModel  @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _id = MutableLiveData<Int>()
    private val _Desc = _id.switchMap { id ->
        repository.getDesc(id)
    }
    val Desc: LiveData<NetworkResult<Desc>> = _Desc

    private val _User = _id.switchMap { id ->
        repository.getSingleUsers(id)

    }
    val User: LiveData<NetworkResult<User>> = _User




    fun getId(id: Int) {
        _id.value = id
    }


}