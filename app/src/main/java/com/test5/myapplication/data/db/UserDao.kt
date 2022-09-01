package com.test5.myapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test5.myapplication.data.model.Desc
import com.test5.myapplication.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAllUser() : LiveData<List<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUser(id: Int): LiveData<User>

    @Query("SELECT * FROM detail WHERE id = :id")
    fun getDesc(id: Int): LiveData<Desc>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAll(Users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insert(User: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(desc: Desc)
}