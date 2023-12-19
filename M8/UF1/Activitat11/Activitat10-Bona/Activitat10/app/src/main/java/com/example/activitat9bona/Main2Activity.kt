package com.example.activitat9bona

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.activitat9bona.databinding.ActivityCarritoBinding
import com.example.activitat9bona.databinding.ActivityMain2Binding
import com.example.activitat9bona.databinding.FragmentHomeBinding

class Main2Activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain2.toolbar)

        binding.appBarMain2.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main2)

        supportActionBar?.title = "Home"

        val header = navView.getHeaderView(0)           // para que este el nombre de usuario en la cabezera del navigation view
        val logo = header.findViewById<ImageView>(R.id.imageView)   //per que la imatge del glide estigui conectada al header
        val text = header.findViewById<TextView>(R.id.textView)     // per que agafi el nom de l'usuari y el posi aqui em eñ header
        text.setText("MediaMarket")

        Glide.with(this)        // posar la foto en el header a partir de una url
            .load("https://1000marcas.net/wp-content/uploads/2020/11/Media-Markt-Logo.png")
            .into(logo)

       // iniciarCarrito()

        if(userBD!![0].tipus ==  0){
            hideItem()
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_home, R.id.nav_movilitat,
                R.id.nav_ordinadors, R.id.nav_smartphones, R.id.nav_home_good,
                R.id.nav_venedor
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when{
            item.itemId == R.id.Carrito ->{
                val intent = Intent(this, Carrito::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun hideItem(){
        var navigationView = findViewById<View>(R.id.nav_view) as (NavigationView)
        var menu  = navigationView.getMenu()
        menu.findItem(R.id.nav_venedor).setVisible(false)
    }

}