package com.test5.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey
    val id: Int,
    val email: String?,
    val first_name: String?,
    val last_name: String?,
    val avatar: String?,
)