package com.example.accessurl

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class RequestTimeouts : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response { //Permitted to retry and make multiple calls
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