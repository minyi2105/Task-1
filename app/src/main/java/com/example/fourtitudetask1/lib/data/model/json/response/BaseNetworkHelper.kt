package com.example.fourtitudetask1.lib.data.model.json.response

import com.example.fourtitudetask1.lib.data.remote.service.OpenTriviaApiService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

object BaseNetworkHelper {
    var BASE_URL:String="https://opentdb.com/"

    val getRetrofitInstance: OpenTriviaApiService
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(OpenTriviaApiService::class.java)
        }
}