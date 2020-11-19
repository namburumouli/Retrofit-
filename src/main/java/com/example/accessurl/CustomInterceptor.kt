package com.example.accessurl

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

class CustomInterceptor: Interceptor {
   // val headerInterceptor=object :Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {  //abstract method of interceptor
           var request:Request=chain.request()
            request=request.newBuilder()
                    .addHeader("X-device-type",Build.DEVICE) //depends on configuration
                    .addHeader("Accept-Language",Locale.getDefault().language) //device language
                    .build()

            val response=chain.proceed(request)// headers send to server
            return response

        }
    }
//}