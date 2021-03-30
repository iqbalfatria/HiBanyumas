package com.tubes.hibanyumas.komentar.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("student_id")
    var student_id: String? = null,
    @SerializedName("class_id")
    var class_id: String? = null,
    @SerializedName("nim")
    var nim: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("gendre")
    var gendre: String? = null,
    @SerializedName("class_name")
    var class_name: String? = null
)