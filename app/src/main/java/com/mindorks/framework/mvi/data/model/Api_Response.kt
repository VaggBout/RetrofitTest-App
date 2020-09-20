package com.mindorks.framework.mvi.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Api_Response (

	@SerializedName("code") val code : Int,
	@SerializedName("meta") val meta : Meta,
	@SerializedName("data") val data : ArrayList<Data>
) : Parcelable



