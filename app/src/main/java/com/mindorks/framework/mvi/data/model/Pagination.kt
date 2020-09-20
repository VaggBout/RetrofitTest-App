package com.mindorks.framework.mvi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pagination (

	@SerializedName("total") val total : Int,
	@SerializedName("pages") val pages : Int,
	@SerializedName("page") val page : Int,
	@SerializedName("limit") val limit : Int
) : Parcelable