package com.test5.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test5.myapplication.data.model.Desc
import com.test5.myapplication.data.model.User

@Database(entities = [User::class, Desc::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun UserDao():UserDao

    companion object{
        @Volatile private var instance: UserDatabase? = null

        fun getDatabase(context:Context) : UserDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }
    private fun buildDatabase(context: Context):UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "User")
            .fallbackToDestructiveMigration()
            .build()
    }
        }
}