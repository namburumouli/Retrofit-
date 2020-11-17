package com.example.accessurl

import retrofit2.Call
import retrofit2.http.GET


 interface IRetrofitAPI {
    @GET("posts") //making get request at end-point
   // fun getPosts(): Call<List<PostModel>>
     fun getposts(): Call<List<PostModel>>
 }


