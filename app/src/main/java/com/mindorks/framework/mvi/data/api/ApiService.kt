package com.mindorks.framework.mvi.data.api

import com.mindorks.framework.mvi.data.model.Api_Response
import retrofit2.http.GET

interface ApiService {

   @GET("users")
   suspend fun getUsers(): Api_Response
}