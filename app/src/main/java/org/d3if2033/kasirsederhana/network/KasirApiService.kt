package org.d3if2033.kasirsederhana.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2033.kasirsederhana.model.Kasir
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raihanki.github.io/datakasir/";

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface KasirApiService {
    @GET("datakasir.json")
    suspend fun getKasir(): List<Kasir>
}

object KasirApi {
    val service: KasirApiService by lazy {
        retrofit.create(KasirApiService::class.java)
    }
}