package org.microg.vending.playapi.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface HttpService {

    @POST
    fun post(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Body body: RequestBody
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST
    fun post(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @FieldMap params: Map<String, String>
    ): Call<ResponseBody>

    @GET
    fun get(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    @GET
    fun get(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @QueryMap params: Map<String, String>
    ): Call<ResponseBody>

    // 🔥 reemplazo de get(url + paramString)
    @GET
    fun getRaw(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    // 🔍 Search (opcional pero útil)
    @GET
    fun getSearchSuggestions(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Query("q") q: String,
        @Query("sb") sb: Int,
        @Query("sst") sstGeneric: Int,
        @Query("sst") sstApp: Int
    ): Call<ResponseBody>

    @GET
    fun getSearchResults(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Query("q") q: String,
        @Query("c") c: Int,
        @Query("ksm") ksm: Int
    ): Call<ResponseBody>

    @GET
    fun getNextSearchResults(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>
}