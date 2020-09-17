package com.mindorks.framework.mvi.data.api

import com.mindorks.framework.mvi.data.model.Api_Response

interface ApiHelper {

    suspend fun getUsers(): Api_Response
}