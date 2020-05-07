package com.example.urbanlist

import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*


interface ApiInterface {

    @Headers("x-rapidapi-key:77dd5bc27dmsheae0d43519446ccp176a48jsnd1ff13df193b")
    @GET("define")
    fun getList(@Query("term") input:String): Call<DataModel>

}