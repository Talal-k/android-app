package com.test5.myapplication.data.model

data class NetworkResult<out T>(val status:Status,val data:T?,val message:String?) {
    enum class Status{
        Success,
        Error,
        Loading
    }
    companion object{
     fun <T> success(data:T):NetworkResult<T>{
         return NetworkResult(Status.Success,data,null)
     }
        fun <T> error(data:T?=null,message: String):NetworkResult<T>{
            return NetworkResult(Status.Error,data,message)
        }
        fun <T> loading(data:T?=null):NetworkResult<T>{
            return NetworkResult(Status.Loading,data,null)
        }
    }
}