package com.test5.myapplication.data.net

import com.test5.myapplication.data.model.UserResult
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userApi: UserApi): UserResult(){
     suspend fun getAllUsers() = getResult {userApi.getAllUsers()}

     suspend fun getSingleUser(id:Int) = getResult { userApi.getSingleUser(id) }

}