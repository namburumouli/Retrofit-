package com.example.accessurl

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val BASEURL = "https://jsonplaceholder.typicode.com"


class ApiClient {
    companion object {

        private var retrofit: Retrofit? = null

        fun getApiClient(): Retrofit {
            val gson = GsonBuilder()
                .setLenient()  // to accept malformed JSON
                .create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) //which logs HTTP request and response data.

            val okHttpClient = OkHttpClient.Builder() //send HTTP requests and read their responses.
                .addInterceptor(interceptor)  //Application interceptor
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

            return retrofit!!
        }
    }


}