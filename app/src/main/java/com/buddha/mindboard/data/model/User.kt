package com.buddha.mindboard.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    val username: String,
    val name: String,
    @SerializedName("profile_image")
    @Expose
    val profileImage: ProfileImage,
    val links: Links
) : Parcelable