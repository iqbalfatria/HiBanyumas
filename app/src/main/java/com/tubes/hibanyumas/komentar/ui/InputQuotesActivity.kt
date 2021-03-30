package com.tubes.hibanyumas.komentar.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tubes.hibanyumas.BuildConfig
import com.tubes.hibanyumas.R
import com.tubes.hibanyumas.komentar.CoroutineContextProvider
import com.tubes.hibanyumas.komentar.MainView
import com.tubes.hibanyumas.komentar.api.MainPresenter
import com.tubes.hibanyumas.komentar.model.Quote
import com.tubes.hibanyumas.komentar.model.Student
import com.vincent.filepicker.Constant.*
import com.vincent.filepicker.activity.ImagePickActivity
import com.vincent.filepicker.filter.entity.ImageFile
import kotlinx.android.synthetic.main.activity_input_quotes.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class InputQuotesActivity : AppCompatActivity(), MainView {

    lateinit var imagename: MultipartBody.Part
    private val addQuoteCode = 1
    private val editQuoteCode = 2
    private lateinit var presenter: MainPresenter
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val REQUEST_CODE = "requestCode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_quotes)

        progressbar.visibility = View.INVISIBLE
        val requestCode = intent.getIntExtra(REQUEST_CODE,0)
        val myData = intent.getParcelableExtra(EXTRA_DATA) as? Quote
        if(myData!= null){
            txtTitle.setText(myData.title)
            txtDescription.setText(myData.description)
            Glide.with(this).load(myData.image).into(imagePreview)
        }
        presenter = MainPresenter(this, CoroutineContextProvider())
        btnSimpan.setOnClickListener{
            if(requestCode==addQuoteCode){
                progressbar.visibility = View.VISIBLE
                presenter.getDetailStudent(BuildConfig.NIM)
            }
            else if(requestCode==editQuoteCode){
                progressbar.visibility = View.VISIBLE
                if (::imagename.isInitialized) {
                    myData?.quote_id?.let { it1 ->
                        presenter.updateQuote(it1,txtTitle.text.toString(),txtDescription.text.toString(),imagename) }
                }
                else{
                    val attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "")
                    imagename = MultipartBody.Part.createFormData("imagename", "", attachmentEmpty);
                    myData?.quote_id?.let { it1 ->
                        presenter.updateQuote(it1,txtTitle.text.toString(),txtDescription.text.toString(),imagename) }
                }
            }
        }
        btnPick.setOnClickListener {
            if(EasyPermissions.hasPermissions(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                val i = Intent(this, ImagePickActivity::class.java)
                i.putExtra(MAX_NUMBER,1)
                startActivityForResult(i, REQUEST_CODE_PICK_IMAGE)
            }else{
                EasyPermissions.requestPermissions(this,"This application need your permission to access photo gallery.",991,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null){
            val pickedImg = data.getParcelableArrayListExtra<ImageFile>(RESULT_PICK_IMAGE)[0]?.path
            val requestBody = RequestBody.create(MediaType.parse("multipart"), File(pickedImg))
            imagename = MultipartBody.Part.createFormData("imagename",File(pickedImg)?.name,requestBody)
            Glide.with(this).load(pickedImg).into(imagePreview)
        }
    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }
    override fun hideLoading() {
        progressbar.visibility = View.INVISIBLE
    }
    override fun showMessage(messsage: String) {
        Toast.makeText(this,messsage, Toast.LENGTH_SHORT).show()
    }
    override fun resultQuote(data: List<Quote>) {
    }
    override fun resultStudent(data: List<Student>) {
        presenter = MainPresenter(this, CoroutineContextProvider())
        data[0].student_id?.let {
            if (::imagename.isInitialized) {
                presenter.addQuote(it,txtTitle.text.toString(), txtDescription.text.toString(), imagename)
            }
            else{
                val attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "")
                imagename = MultipartBody.Part.createFormData("imagename", "", attachmentEmpty);
                presenter.addQuote(it,txtTitle.text.toString(), txtDescription.text.toString(), imagename)
            }
        }
    }
    override fun resultAction(data: String) {
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_hapus_crud, menu)
        val requestCode = intent.getIntExtra(REQUEST_CODE,0)
        if(requestCode==addQuoteCode){
            menu!!.findItem(R.id.delete).isVisible = false // jika activity dibuka untuk tambah data maka tombol hapus di sembunyikan
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.delete) {
            val myData = intent.getParcelableExtra(EXTRA_DATA) as Quote
            val builder = AlertDialog.Builder(this@InputQuotesActivity)
            builder.setTitle("Delete "+myData!!.title)
            builder.setMessage("Are you sure to delete "+ myData!!.title +"?")
            builder.setPositiveButton("YES"){dialog, which ->
                presenter = MainPresenter(this, CoroutineContextProvider())
                myData.quote_id?.let { presenter.deleteQuote(it) }
                finish()
            }
            builder.setNegativeButton("No"){dialog,which ->
                Toast.makeText(applicationContext,"You are not sure.", Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
