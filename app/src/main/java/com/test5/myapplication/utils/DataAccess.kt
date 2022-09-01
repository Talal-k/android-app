package com.test5.myapplication.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map

import com.test5.myapplication.data.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val myExecutor: ExecutorService = Executors.newSingleThreadExecutor{ task ->
    Thread(task).also { it.isDaemon = true }
}

val myDispatcher = myExecutor.asCoroutineDispatcher()

fun <T, A> dataProcess(DatabaseQuery: () -> LiveData<T>,
                       NetworkRequest: suspend () -> NetworkResult<A>,
                       saveResult: suspend(A) -> Unit):LiveData<NetworkResult<T>> =  liveData(
    myDispatcher){

          emit(NetworkResult.loading())
      val data = DatabaseQuery.invoke().map { NetworkResult.success(it) }
       emitSource(data)
    val response = NetworkRequest.invoke()
    if(response.status == NetworkResult.Status.Success){
        saveResult(response.data!!)

    }
    else if(response.status == NetworkResult.Status.Error){
        emit(NetworkResult.error(null,response.message!!))
        emitSource(data)


    }
}