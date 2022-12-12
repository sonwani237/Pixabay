package com.exchange.repository

import android.app.Application
import com.exchange.constant.ApiConstant
import com.exchange.model.SearchPics
import retrofit2.Response

class DashboardRepo(application: Application) : BaseRepository(application) {

    suspend fun fetchPics(query: String): Response<SearchPics> {
        return apiInterface.fetchPics(ApiConstant.KEY, query, ApiConstant.TYPE, ApiConstant.PRETTY)
    }

}