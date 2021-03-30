package com.tubes.hibanyumas.komentar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tubes.hibanyumas.R
import com.tubes.hibanyumas.komentar.model.Quote
import kotlinx.android.synthetic.main.activity_komentar_detail.*

class KomentarDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val REQUEST_CODE = "requestCode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komentar_detail)
        val myData = intent.getParcelableExtra(InputQuotesActivity.EXTRA_DATA) as? Quote
        tv_title_quote.text= myData!!.title
        tv_quote.text= myData!!.description
        Glide.with(this).load(myData!!.image).into(image_quote)
        tv_student_info.text= myData!!.name + " | " + myData.className
        tv_created.text= "Dibuat pada :"+myData!!.created
        tv_updated.text= "Diubah pada :"+myData!!.updated
    }
}
