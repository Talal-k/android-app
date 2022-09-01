package com.test5.myapplication.data.model

import android.util.Log
import retrofit2.Response

abstract class UserResult {

    suspend fun <T> getResult(call:  suspend() -> Response<T>):NetworkResult<T>{
   try {
       val response = call()
       if(response.isSuccessful){
           val body = response.body()
           body?.let {
               return NetworkResult.success(body)
           }
       }
       return error(" ${response.code()} ${response.message()}")

   }catch (e:Exception){
       return error(e.message ?: e.toString())
   }
    }
    private fun <T> error(message: String):NetworkResult<T>{
        Log.e("remoteDataSource", message)
        return NetworkResult.error(null,"netwrok request for the follwing reason: $message")
    }
}