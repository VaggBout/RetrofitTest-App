package com.mindorks.framework.mvi.data.model

import com.google.gson.annotations.SerializedName

data class Api_Response (

	@SerializedName("code") val code : Int,
	@SerializedName("meta") val meta : Meta,
	@SerializedName("data") val data : List<Data>
)


