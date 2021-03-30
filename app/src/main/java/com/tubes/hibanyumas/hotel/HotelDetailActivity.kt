package com.tubes.hibanyumas.hotel

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tubes.hibanyumas.R
import com.tubes.hibanyumas.komentar.KomentarActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.toolbar
import kotlinx.android.synthetic.main.content_detail.*

class HotelDetailActivity: AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        val myData = intent.getParcelableExtra(EXTRA_MYDATA) as MyData
        supportActionBar?.title = myData.name.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tv_detail_description.text = myData.description.toString()
        fab.setOnClickListener { view ->
            val intent = Intent(this, KomentarActivity::class.java)
            startActivity(intent)
        }


        Glide.with(this)
            .load(myData.photo.toString())
            .apply(RequestOptions().override(700, 700))
            .into(iv_detail_photo)
    }
    companion object {
        const val EXTRA_MYDATA = "extra_mydata"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

