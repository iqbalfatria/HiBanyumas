package com.tubes.hibanyumas.komentar.api

import com.tubes.hibanyumas.komentar.model.Message
import com.tubes.hibanyumas.komentar.model.QuoteResponse
import com.tubes.hibanyumas.komentar.model.StudentResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("student/q/nim/{nim}")
    fun getStudentByNim(@Path("nim") nim: String): Call<StudentResponse>
    @GET("quotes/q/my_quote/{nim}")
    fun getMyQuotes(@Path("nim") nim:String): Call<QuoteResponse>
    @GET("quotes/q/class_id/{class_id}")
    fun getClassQuotes(@Path("class_id") class_id:String): Call<QuoteResponse>
    @GET("quotes/")
    fun getAllQuotes(): Call<QuoteResponse>
    @POST("quotes/")
    @Multipart
    fun addQuote(
        @Part("student_id") class_id: RequestBody,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part imagename: MultipartBody.Part?
    ): Call<Message>
    @POST("quotes/q/edit/1")
    @Multipart
    fun updateQuote(
        @Part("quote_id") quote_id: RequestBody,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part imagename: MultipartBody.Part?
    ): Call<Message>
    @DELETE("quotes/q/quote_id/{quote_id}")
    fun deleteQuote(
        @Path("quote_id") quote_id: String
    ): Call<Message>
}