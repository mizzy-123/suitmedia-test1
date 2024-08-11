package com.suitmedia.test1.api

import com.suitmedia.test1.data.model.response.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 10
    ): UsersResponse
}