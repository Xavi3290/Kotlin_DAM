package com.example.provaretrofit

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.provaretrofit.API.CrudApi
import com.example.provaretrofit.BD.BD
import com.example.provaretrofit.databinding.ActivityNavigationDrawerBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NavigationDrawer : AppCompatActivity()/*, CoroutineScope */{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding
    private lateinit var db: BD
   /* private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNavigationDrawer.toolbar)

        binding.appBarNavigationDrawer.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)

        supportActionBar?.title = "Home"

        val header = navView.getHeaderView(0)           // para que este el nombre de usuario en la cabezera del navigation view
        val logo = header.findViewById<ImageView>(R.id.imageView)   //per que la imatge del glide estigui conectada al header
        val text = header.findViewById<TextView>(R.id.textView)     // per que agafi el nom de l'usuari y el posi aqui em eñ header
        text.setText("MediaMarket")

        Glide.with(this)        // posar la foto en el header a partir de una url
            .load("https://1000marcas.net/wp-content/uploads/2020/11/Media-Markt-Logo.png")
            .into(logo)

     /*   runBlocking {                   // si no aparecen las imagenes al principio despues de esperar ponerlo en el primer
            val crudApi = CrudApi()                     // fragmento que se vea
            val corrutina = launch {
                listProducte = crudApi.getAllFromAPI() as ArrayList<Producte>
            }
            corrutina.join()
        }*/




        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_smartphones, R.id.nav_computers,
                R.id.nav_home_good,R.id.nav_modificar
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}