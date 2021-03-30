package com.tubes.hibanyumas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.tubes.hibanyumas.hotel.RecycleView
import com.tubes.hibanyumas.komentar.KomentarActivity
import com.tubes.hibanyumas.kuliner.RecycleView5
import com.tubes.hibanyumas.oleholeh.RecycleView2
import com.tubes.hibanyumas.tempatbersejarah.RecycleView4
import com.tubes.hibanyumas.wisataalam.RecycleView3
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    internal lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hotel.setOnClickListener{
            val intent = Intent(this, RecycleView::class.java)
            startActivity(intent)
        }
        kuliner.setOnClickListener{
            val intent = Intent(this, RecycleView5::class.java)
            startActivity(intent)
        }
        oleholeh.setOnClickListener{
            val intent = Intent(this, RecycleView2::class.java)
            startActivity(intent)
        }
        tempatbersejarah.setOnClickListener{
            val intent = Intent(this, RecycleView4::class.java)
            startActivity(intent)
        }
        wisataalam.setOnClickListener{
            val intent = Intent(this, RecycleView3::class.java)
            startActivity(intent)
        }
        comment.setOnClickListener{
            val intent = Intent(this, KomentarActivity::class.java)
            startActivity(intent)
        }

        viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        val adapter = ViewPageAdapter(this)
        viewPager.adapter = adapter
    }
}