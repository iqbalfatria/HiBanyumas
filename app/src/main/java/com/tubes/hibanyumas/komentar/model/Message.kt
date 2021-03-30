package com.tubes.hibanyumas.komentar.model

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("error")
    var error: String? = null,
    @SerializedName("message")
    var message: String? = null
)