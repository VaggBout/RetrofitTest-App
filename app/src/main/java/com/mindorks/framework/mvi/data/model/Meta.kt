package com.mindorks.framework.mvi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta (

	@SerializedName("pagination") val pagination : Pagination
) : Parcelable