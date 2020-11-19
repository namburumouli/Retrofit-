package com.example.accessurl

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


const val BASEURL = ""


class ApiClient {
    companion object {

        private var retrofit: Retrofit? = null

        fun getApiClient(): Retrofit {
            val gson = GsonBuilder()
                .setLenient()  // to accept malformed JSON
                .create()

            // log request and responses
            val logging = HttpLoggingInterceptor() ////Logs Request and Response along with header and if body present in the API call.
            logging.level = HttpLoggingInterceptor.Level.BODY


            //send HTTP requests and read their responses
            val httpClient = OkHttpClient.Builder()
                    .addInterceptor(CustomInterceptor())
                    .addInterceptor(RequestTimeouts())
                    .addInterceptor(logging)
                    .addInterceptor(LogInterceptor())

                    .build()



         /*  val okHttpClient = OkHttpClient.Builder() //send HTTP requests and read their responses.
                //.addInterceptor(logging)
                //   .addInterceptor(LogInterceptor())
             //   .readTimeout(100, TimeUnit.SECONDS) //socket time
               // .connectTimeout(100, TimeUnit.SECONDS) //connect time
                //.callTimeout(2, TimeUnit.MINUTES) //server processing, and reading the response body.
                //.writeTimeout(30, TimeUnit.SECONDS) //individual write IO operations
                .build()*/


            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            }
            return retrofit!!
            //TODO:create a instance
        }
    }



}


