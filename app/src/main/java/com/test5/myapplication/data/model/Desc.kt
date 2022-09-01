package com.test5.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "detail")
data class Desc(
    @PrimaryKey
    val id:Int,
    val url: String?,
    val text: String?,
)