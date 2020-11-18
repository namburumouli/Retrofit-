package com.example.accessurl

import retrofit2.Call
import retrofit2.http.*


interface IRetrofitAPI {
    @GET(NetworkConstant.DATA) //making get request at end-point
     fun getposts(): Call<List<PostModel>>

     @POST(NetworkConstant.DATA)
     fun createPost(@Body postModel: PostModel):Call<PostModel>

    @DELETE(NetworkConstant.DATA)
    fun deletePost(@Path("id") id:Int):Call<String>

 }


