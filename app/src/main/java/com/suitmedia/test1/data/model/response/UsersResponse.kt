package com.suitmedia.test1.data.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class UsersResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<ListUsers>
)

@Entity(tableName = "users")
data class ListUsers(
    @PrimaryKey
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)
