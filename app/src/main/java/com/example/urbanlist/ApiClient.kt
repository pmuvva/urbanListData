package com.example.urbanlist

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    var BASE_URL:String="https://mashape-community-urban-dictionary.p.rapidapi.com/"
    private var API_KEY= "77dd5bc27dmsheae0d43519446ccp176a48jsnd1ff13df193b"
    val getClient: ApiInterface
        get() {

            val gson = GsonBuilder()
                .setLenient()
                .create() //x-rapidapi-key
                val interceptor = Interceptor { chain ->
                val url = chain.request().url().newBuilder().addQueryParameter("user-key", API_KEY).build()
                //   vval o
                    val request = chain.request()
                    .newBuilder()
               //     .addHeader("x-rapidapi-key",API_KEY)
                    .url(url)
                    .build()
                chain.proceed(request)
            }


          //  val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()
            val apiClient = OkHttpClient().newBuilder().build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(apiClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)

        }

}
