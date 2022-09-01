package com.test5.myapplication.data.net

import com.test5.myapplication.data.model.ListUsers
import com.test5.myapplication.data.model.SingleUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getAllUsers():Response<ListUsers>

    @GET("users/{id}")
    suspend fun getSingleUser(@Path("id") id:Int ):Response<SingleUser>

}