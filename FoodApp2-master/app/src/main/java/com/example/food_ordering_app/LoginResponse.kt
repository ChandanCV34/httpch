package com.example.food_ordering_app

data class LoginResponse( var id:Int,
                          var statusCode:Int,
                           var token: String,
                           var email:String,
                           var memberSince: Int,
                           var password:String?,
var user: User)
