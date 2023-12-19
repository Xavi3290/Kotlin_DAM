package com.example.activitat7

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
import com.example.activitat7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuari = intent.getStringExtra("usuari").toString()
        val pass = intent.getStringExtra("pass").toString()
        if(pass.contentEquals("1234") && usuari.contentEquals("xavi")){
            Toast.makeText(this,"Benvingut", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"No pots accedir", Toast.LENGTH_LONG).show()
            finish()    //para que cierre la activity
        }

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        val header = navView.getHeaderView(0)           // para que este el nombre de usuario en la cabezera del navigation view
        val logo = header.findViewById<ImageView>(R.id.imageView)   //per que la imatge del glide estigui conectada al header
        val text = header.findViewById<TextView>(R.id.textView)     // per que agafi el nom de l'usuari y el posi aqui em eñ header
        text.setText(usuari)

        Glide.with(this)        // posar la foto en el header a partir de una url
            .load("http://www.iescarlesvallbona.cat/images/imatges/banner_ins.png")
            .into(logo)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_M6UF1, R.id.nav_M6UF2, R.id.nav_M6UF3, R.id.nav_M6UF4,     // te que tindre el mateix id en el mobile_navigation i en el activity_main_drawer
                R.id.nav_M7UF1, R.id.nav_M7UF2,
                R.id.nav_M8UF1, R.id.nav_M8UF2, R.id.nav_M8UF3,
                R.id.nav_M9UF1, R.id.nav_M9UF2, R.id.nav_M9UF3,
                R.id.nav_M10UF1, R.id.nav_M10UF2,
                R.id.nav_M13UF1,
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)  // aqui no es posa true perque entraria en conflicte amb el altre menu perque sino falla, millor fer el super
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       //per que el menu de la dreta funcioni
        when{
            item.itemId == R.id.mMoodle -> goURL("https://moodle.iescarlesvallbona.cat/")
            item.itemId == R.id.mCorreu -> goURL("https://www.google.com/intl/es/gmail/about/")

        }
        return super.onOptionsItemSelected(item)        // aqui no es posa true perque sino falla, millor fer el super
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun goURL(url:String){      // intent implicit per que et vagi a una web
        // Obrir una pagina web en el navegador predeterminat
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}