package com.tubes.hibanyumas.komentar

import com.tubes.hibanyumas.komentar.model.Quote
import com.tubes.hibanyumas.komentar.model.Student

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMessage(messsage : String)
    fun resultQuote(data: List<Quote>)
    fun resultStudent(data: List<Student>)
    fun resultAction(data: String)
}