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


const val BASEURL = "https://jsonplaceholder.typicode.com"


class ApiClient {
    companion object {

        private var retrofit: Retrofit? = null

        fun getApiClient(): Retrofit {
            val gson = GsonBuilder()
                .setLenient()  // to accept malformed JSON
                .create()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY // TODO: fixed deprication
            val httpClient = OkHttpClient.Builder()//Logs Request and Response along with header and if body present in the API call.
            httpClient.addInterceptor(logging)


           val okHttpClient = OkHttpClient.Builder() //send HTTP requests and read their responses.
                .addInterceptor(logging)
             //   .readTimeout(100, TimeUnit.SECONDS) //socket time
               // .connectTimeout(100, TimeUnit.SECONDS) //connect time
                //.callTimeout(2, TimeUnit.MINUTES) //server processing, and reading the response body.
                //.writeTimeout(30, TimeUnit.SECONDS) //individual write IO operations
                .build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            }
            // TODO: check response blog

            return retrofit!!
        }
    }

    @Throws(IOException::class)
    fun intercept(chain: Interceptor.Chain): Response? {
        val request: Request = chain.request()
        val readTimeOutStr = request.header("Read-Time-Out")
        val writeTimeOutStr = request.header("Write-Time-Out")
        val connectTimeOutStr = request.header("Connect-Time-Out")
        val readTimeOut = readTimeOutStr?.toInt() ?: chain.readTimeoutMillis()
        val writeTimeOut = writeTimeOutStr?.toInt() ?: chain.writeTimeoutMillis()
        val connectTimeOut = connectTimeOutStr?.toInt() ?: chain.connectTimeoutMillis()
        val overrideChain: Interceptor.Chain = chain.withReadTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                .withWriteTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                .withConnectTimeout(connectTimeOut, TimeUnit.MILLISECONDS)
        return overrideChain.proceed(request)
    }

}


