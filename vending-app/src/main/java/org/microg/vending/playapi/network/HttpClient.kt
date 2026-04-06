package org.microg.vending.playapi.network

import org.microg.vending.playapi.GooglePlayApi
import org.microg.vending.playapi.data.models.PlayResponse
import okhttp3.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object HttpClient {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .cache(Cache(File("okhttp_cache"), 50L * 1024 * 1024))
        .retryOnConnectionFailure(true)
        .followRedirects(true)
        .followSslRedirects(true)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(GooglePlayApi.URL_BASE)
        .client(okHttpClient)
        .build()

    private val httpService: HttpService = retrofit.create()

    @Throws(IOException::class)
    fun post(
        url: String,
        headers: Map<String, String>,
        requestBody: RequestBody
    ): PlayResponse {
        val call = httpService.post(url, headers, requestBody)
        return buildPlayResponse(call.execute())
    }

    @Throws(IOException::class)
    fun post(
        url: String,
        headers: Map<String, String>,
        params: Map<String, String>
    ): PlayResponse {
        val call = httpService.post(url, headers, params)
        return buildPlayResponse(call.execute())
    }

    @Throws(IOException::class)
    fun get(
        url: String,
        headers: Map<String, String>
    ): PlayResponse {
        val call = httpService.get(url, headers)
        return buildPlayResponse(call.execute())
    }

    @Throws(IOException::class)
    fun get(
        url: String,
        headers: Map<String, String>,
        params: Map<String, String>
    ): PlayResponse {
        val call = httpService.get(url, headers, params)
        return buildPlayResponse(call.execute())
    }

    @Throws(IOException::class)
    fun getX(
        url: String,
        headers: Map<String, String>,
        paramString: String
    ): PlayResponse {
        val call = httpService.getRaw(url + paramString, headers)
        return buildPlayResponse(call.execute())
    }

    private fun buildPlayResponse(response: Response<ResponseBody>): PlayResponse {
        return PlayResponse().apply {
            response.body()?.let {
                responseBytes = it.bytes()
            }
            response.errorBody()?.let {
                errorBytes = it.bytes()
                errorString = String(errorBytes)
            }
            isSuccessful = response.isSuccessful
            code = response.code()
        }.also {
            println(response.raw())
        }
    }
}