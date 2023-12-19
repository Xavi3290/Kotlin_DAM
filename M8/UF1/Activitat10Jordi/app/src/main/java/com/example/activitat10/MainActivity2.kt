package com.example.activitat10

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.activitat10.database.ProducteCarret
import com.example.activitat10.database.database
import com.example.activitat10.databinding.ActivityMain2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity2 : AppCompatActivity(), CoroutineScope {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding
    private lateinit var db: database
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        omplirDadesPortada()
        omplirComputer()
        omplirCarretBD()
        usuariActual = intent.getStringExtra("nomusu").toString()

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            print(llistaCarret)
            val intent = Intent(this, ShoppingCarv2::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        navView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_ini, R.id.computers, R.id.mobility, R.id.smartphones, R.id.tv
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.getHeaderView(0).findViewById<TextView>(R.id.nomUsu).setText(usuariActual)

    }

    private fun omplirCarretBD() {
        launch {
            db = database.getDatabase(applicationContext)
            var llista = db.DaoProductes().selectAllProducteCarret(usuariActual) as ArrayList<ProducteCarret>
            if (llista.size>0){
                llistaCarret = llista
            }else{
                llistaCarret.clear()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity2, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}