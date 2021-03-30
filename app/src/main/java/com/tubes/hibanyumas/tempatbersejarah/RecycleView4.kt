package com.tubes.hibanyumas.tempatbersejarah

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.tubes.hibanyumas.R
import com.tubes.hibanyumas.tempatbersejarah.adapter4.ListMyDataAdapter4
import kotlinx.android.synthetic.main.activity_recycle_view.*
import kotlinx.android.synthetic.main.activity_recycle_view4.*

class RecycleView4 : AppCompatActivity() {

    private val list = ArrayList<MyData>()

    fun getListMyDatas(): ArrayList<MyData> {
        val dataName = resources.getStringArray(R.array.data_name4)
        val dataDescription = resources.getStringArray(R.array.data_description4)
        val dataPhoto = resources.getStringArray(R.array.data_photo4)
        val listMyData = ArrayList<MyData>()
        for (position in dataName.indices) {
            val myData = MyData(
                dataName[position],
                dataDescription[position],
                dataPhoto[position],
                12.6,
                12.6
            )
            listMyData.add(myData)
        }
        return listMyData
    }


    private fun showRecyclerList() {
        rv_mydata4.layoutManager = LinearLayoutManager(this)
        val listMyDataAdapter = ListMyDataAdapter4(list, this@RecycleView4)
        rv_mydata4.adapter = listMyDataAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view4)

        rv_mydata4.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val manager= getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
//                searchView.setQuery("SDFSD")
                searchItem.collapseActionView()

//                Toast.makeText("sdfsd","SDF")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }
}
