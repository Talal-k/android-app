package com.test5.myapplication.data.Repository

import com.test5.myapplication.data.db.UserDao
import com.test5.myapplication.data.net.UserRemoteDataSource
import com.test5.myapplication.utils.dataProcess
import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: UserDao, private val remoteDataSource: UserRemoteDataSource) {

      fun getAllUsers()= dataProcess(
          DatabaseQuery = { localDataSource.getAllUser() },
          NetworkRequest = { remoteDataSource.getAllUsers() },
          saveResult = { localDataSource.insertAll(it.data)}
      )
    fun getSingleUsers(id:Int)= dataProcess(
        DatabaseQuery = { localDataSource.getUser(id) },
        NetworkRequest = { remoteDataSource.getSingleUser(id) },
        saveResult = { localDataSource.insert(it.data)}
    )
    fun getDesc(id:Int)= dataProcess(
        DatabaseQuery = { localDataSource.getDesc(id) },
        NetworkRequest = { remoteDataSource.getSingleUser(id) },
        saveResult = { localDataSource.insertDetail(it.support)}
    )
}