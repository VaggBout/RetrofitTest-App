package com.mindorks.framework.mvi.data.api

import com.mindorks.framework.mvi.data.model.Api_Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Api_Response {
        return apiService.getUsers()
    }
}