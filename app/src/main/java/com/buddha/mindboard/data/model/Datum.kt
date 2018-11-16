package com.buddha.mindboard.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Datum(
    var id: String = "",
    @SerializedName("created_at") @Expose val createdAt: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val color: String = "",
    val likes: Int = 0,
    @SerializedName("liked_by_user") @Expose val likedByUser: Boolean = false,
    val user: User? = null,
    val urls: Urls? = null,
    val categories: List<Category> = arrayListOf(),
    val links: Links? = Links()
) : Parcelable
