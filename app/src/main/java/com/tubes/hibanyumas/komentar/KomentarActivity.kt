package com.tubes.hibanyumas.komentar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.tubes.hibanyumas.BuildConfig
import com.tubes.hibanyumas.R
import com.tubes.hibanyumas.komentar.api.MainPresenter
import com.tubes.hibanyumas.komentar.model.Quote
import com.tubes.hibanyumas.komentar.model.Student
import kotlinx.android.synthetic.main.nav_header_komentar.view.*

private lateinit var appBarConfiguration: AppBarConfiguration

class KomentarActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komentar)

        presenter = MainPresenter(this, CoroutineContextProvider())
        presenter.getDetailStudent(BuildConfig.NIM)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_my_quotes, R.id.nav_global_quotes
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(messsage: String) {
        Toast.makeText(this,messsage, Toast.LENGTH_SHORT).show()
    }

    override fun resultQuote(data: List<Quote>) {

    }

    override fun resultStudent(data: List<Student>) {
        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerView = navView.getHeaderView(0)
        data[0].name?.let{
            headerView.nav_header_title.text = it
        }
        data[0].nim?.let{
            headerView.nav_header_nim.text = it
        }
        data[0].class_name?.let{
            headerView.nav_header_class.text = it
        }
    }

    override fun resultAction(data: String) {
    }
}
