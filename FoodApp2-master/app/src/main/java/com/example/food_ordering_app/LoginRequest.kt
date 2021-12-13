package com.example.food_ordering_app

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    var email:String,
    @SerializedName("password")
    var password:String)
