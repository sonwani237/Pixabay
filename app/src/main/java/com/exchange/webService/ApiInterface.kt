package com.exchange.webService

import com.exchange.constant.ApiConstant
import com.exchange.model.SearchPics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConstant.FLOWER)
    suspend fun fetchPics(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") image_type: String,
        @Query("pretty") pretty: Boolean
    ): Response<SearchPics>

}