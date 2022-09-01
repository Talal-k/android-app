package com.test5.myapplication.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test5.myapplication.data.Repository.Repository
import com.test5.myapplication.data.model.NetworkResult
import com.test5.myapplication.data.model.User


class MainViewModel @ViewModelInject constructor(private val repository: Repository):ViewModel() {

   val users:LiveData<NetworkResult<List<User>>> = repository.getAllUsers()

}