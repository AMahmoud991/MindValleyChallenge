package com.buddha.mindboard.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val title: String,
    @SerializedName("photo_count") @Expose val photoCount: Int,
    val links: Links
) : Parcelable