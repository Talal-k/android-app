package com.test5.myapplication.data.model

data class ListUsers (
    val page: Int,
    val per_page: Int,
    val total:Int,
    val total_pages:Int,
    val data: List<User>
        )