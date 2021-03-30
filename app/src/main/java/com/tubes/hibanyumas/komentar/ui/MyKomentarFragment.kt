package com.tubes.hibanyumas.komentar.ui


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tubes.hibanyumas.BuildConfig

import com.tubes.hibanyumas.R
import com.tubes.hibanyumas.komentar.CoroutineContextProvider
import com.tubes.hibanyumas.komentar.MainView
import com.tubes.hibanyumas.komentar.api.MainPresenter
import com.tubes.hibanyumas.komentar.model.Quote
import com.tubes.hibanyumas.komentar.model.Student
import kotlinx.android.synthetic.main.fragment_komentar_quotes.*
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class MyKomentarFragment : Fragment(), MainView {

    private lateinit var presenter: MainPresenter
    private var quotes: MutableList<Quote> = mutableListOf()
    private lateinit var adapter: MyKomentarAdapter
    private val addQuoteCode = 1
    private val editQuoteCode = 2
    private val deleteQuoteCode = 3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_komentar_quotes, container, false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview_global_quotes.layoutManager = LinearLayoutManager(activity)
        adapter = MyKomentarAdapter(quotes, activity!!)
        recyclerview_global_quotes.adapter = adapter
        presenter = MainPresenter(this, CoroutineContextProvider())
        progressbar.visibility = View.VISIBLE
        presenter.getMyQuotes(BuildConfig.NIM)
        fab.setOnClickListener { view ->
            val intent = Intent(activity!!, InputQuotesActivity::class.java)
            intent.putExtra(InputQuotesActivity.REQUEST_CODE, addQuoteCode);
            startActivityForResult(intent, addQuoteCode)
        }
        adapter.setOnItemClickCallback(object : MyKomentarAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Quote) {
                val intent = Intent(activity!!, InputQuotesActivity::class.java)
                intent.putExtra(InputQuotesActivity.REQUEST_CODE, editQuoteCode);
                intent.putExtra(InputQuotesActivity.EXTRA_DATA, data)
                startActivityForResult(intent,editQuoteCode)
            }
        })
        swiperefresh.onRefresh {
            progressbar.visibility = View.INVISIBLE
            presenter.getMyQuotes(BuildConfig.NIM)
        }
    }

    override fun resultAction(data: String) {
    }
    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }
    override fun hideLoading() {
        progressbar.visibility = View.INVISIBLE
    }
    override fun resultQuote(data: List<Quote>) {
        quotes.clear()
        quotes.addAll(data)
        adapter.notifyDataSetChanged()
        swiperefresh.isRefreshing = false
    }
    override fun resultStudent(data: List<Student>) {
    }
    override fun showMessage(messsage: String) {
        Toast.makeText(activity!!,messsage, Toast.LENGTH_SHORT).show()
    }
    override fun onResume() {
        super.onResume()
        progressbar.visibility = View.INVISIBLE
        presenter. getMyQuotes(BuildConfig.NIM)
    }

}
