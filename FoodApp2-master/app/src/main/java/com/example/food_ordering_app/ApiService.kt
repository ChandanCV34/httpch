package com.example.food_ordering_app

import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import java.net.CacheRequest

interface ApiService {
   @POST(Constants.Register_URL)
    suspend fun Register(@Body request: RegisterUser)

    @POST(Constants.LOGIN_URL)
  // @FormUrlEncoded
  //  @Multipart
    suspend fun login(@Body request: LoginRequest): Call<LoginResponse>
}