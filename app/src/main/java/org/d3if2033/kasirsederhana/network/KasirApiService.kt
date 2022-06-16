package org.d3if2033.kasirsederhana.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raihanki.github.io/datakasir/";

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface KasirApiService {
    @GET("datakasir.json")
    suspend fun getKasir(): String
}

object KasirApi {
    val service: KasirApiService by lazy {
        retrofit.create(KasirApiService::class.java)
    }
}