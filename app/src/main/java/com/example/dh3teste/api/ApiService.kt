package com.example.dh3teste.api

import com.example.dh3teste.utils.Constants.marvelAPI.BASE_URL
import com.example.dh3teste.utils.Constants.marvelAPI.QUERY_NAME_APIKEY
import com.example.dh3teste.utils.Constants.marvelAPI.QUERY_VALUE_APIKEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object ApiService {
    val marvelApi: MarvelAPI = getMarvelClient().create(MarvelAPI::class.java)

    private fun getMarvelClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptorMarvelClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getInterceptorMarvelClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor {chain ->
                val newRequest = chain.request().newBuilder()
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor { chain ->
                val currentTimestamp = System.currentTimeMillis()
                val url = chain.request().url().newBuilder()
                    .addQueryParameter("hash",
                        toMD5Hash(currentTimestamp.toString() + "0dd0c16fedb8a02985977eafca66b49f5e6a526f" + "6eb7e8896ec5850c52515a8a23ee97f0"))
                    .addQueryParameter("ts", currentTimestamp.toString())
                    .addQueryParameter("format", "comic")
                    .addQueryParameter("formatType", "comic")
                    .addQueryParameter("characters", "1009407")
                    .addQueryParameter("noVariants", "true")
                    .addQueryParameter(QUERY_NAME_APIKEY, QUERY_VALUE_APIKEY)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
        return  interceptor.build()
    }

    private fun toMD5Hash(toBeEncrypt: String): String {
        var pass = toBeEncrypt
        var encryptedString: String? = null
        val md5: MessageDigest
        try {
            md5 = MessageDigest.getInstance("MD5")
            md5.update(pass.toByteArray(), 0, pass.length)
            pass = BigInteger(1, md5.digest()).toString(16)
            while (pass.length < 32) {
                pass = "0$pass"
            }
            encryptedString = pass
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }
        return encryptedString ?: ""
    }
}