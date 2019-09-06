package com.example.fourtitudetask1.lib.data.remote.service

import com.example.fourtitudetask1.lib.data.model.json.response.Category
import com.example.fourtitudetask1.lib.data.model.json.response.QuestionCategory
import com.example.fourtitudetask1.lib.data.model.json.response.QuestionResponse
import com.example.fourtitudetask1.lib.data.model.json.response.SessionToken
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTriviaApiService {

    @GET("api_category.php")
    fun getCategoryResponseBody(): Observable<Category>

    @GET("api_token.php")
    fun getSessionTokenResponseBody(@Query("command") command: String): Observable<SessionToken>

    @GET("api_count.php")
    fun getQuestionCountByIdResponseBody(@Query("category") category: String): Observable<QuestionCategory>

    @GET("api.php")
    fun getQuestionResponseBody(@Query("token") token: String,
                                @Query("amount") amount: Int,
                                @Query("category") category: Int?,
                                @Query("difficulty") difficulty: String?,
                                @Query("type") type: String?): Observable<QuestionResponse>

    @GET("api_token.php")
    fun getTokenResetResponseBody(@Query("token") token: String,
                                  @Query("command") command: String): Observable<QuestionResponse>

    companion object Factory {
        fun create(): OpenTriviaApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl("https://opentdb.com/")
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(OpenTriviaApiService::class.java)
        }
    }
}