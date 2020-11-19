package com.example.accessurl

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.http2.Http2Reader.Companion.logger
import java.io.IOException
import java.lang.String


//logs the outgoing request and the incoming response.
class LogInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()// chain to get the reference
        val t1 = System.nanoTime()
        logger.info(String.format("Sending request %s on %s%n%s", request.url, chain.connection(), request.headers))
        val response = chain.proceed(request) //Permitted to retry and make multiple calls
        val t2 = System.nanoTime() //returns the current value of the running Java Virtual Machine's
        logger.info(String.format("Received response for %s in %.1fms%n%s", response.request.url, (t2 - t1) / 1e6, response . headers))
        return response
    }
}