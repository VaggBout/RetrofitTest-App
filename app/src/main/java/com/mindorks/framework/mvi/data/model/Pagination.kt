package com.mindorks.framework.mvi.data.model

import com.google.gson.annotations.SerializedName

data class Pagination (

	@SerializedName("total") val total : Int,
	@SerializedName("pages") val pages : Int,
	@SerializedName("page") val page : Int,
	@SerializedName("limit") val limit : Int
)