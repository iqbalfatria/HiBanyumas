package com.tubes.hibanyumas.komentar.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quote(
    @SerializedName("quote_id")
    var quote_id: String? = null,
    @SerializedName("student_id")
    var student_id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("nim")
    var nim: String? = null,
    @SerializedName("class")
    var className: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("images")
    var image: String? = null,
    @SerializedName("created")
    var created: String? = null,
    @SerializedName("updated")
    var updated: String? = null
): Parcelable