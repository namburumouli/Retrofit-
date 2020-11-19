package com.example.accessurl

import okhttp3.*
import retrofit2.Retrofit
import java.io.IOException

class RequestURl :Interceptor {

    var requesturl: String = ""

    fun getRequestUrl(): String {
        return requesturl
    }

    fun setRequestUrl(requestUrl: String) {
        requesturl = requestUrl
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request() //Observes, modifies, and potentially  requests
        val response: Response = chain.proceed(request) // signal that we want to hit the APInow
        setRequestUrl(response.request.url.toString())
        return response

    }

}


