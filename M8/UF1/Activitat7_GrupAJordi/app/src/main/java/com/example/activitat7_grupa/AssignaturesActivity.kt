package com.example.activitat7_grupa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.example.activitat7_grupa.databinding.ActivityAssignaturesBinding

class AssignaturesActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAssignaturesBinding

    lateinit var nomusuari : String
    lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAssignaturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nomusuari = intent.getStringExtra("nomusuari").toString()
        password = intent.getStringExtra("password").toString()

        if (nomusuari!="Jordi" || password!="111"){
            finish()
        }

        setSupportActionBar(binding.appBarAssignatures.toolbar)

        binding.appBarAssignatures.fab.setOnClickListener { view ->
            val intent = Intent(this, EnviarEmail::class.java)
            intent.putExtra("nomusuari", nomusuari)
            startActivity(intent)

        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val header = navView.getHeaderView(0)
        header.findViewById<TextView>(R.id.nomusuari2).setText(nomusuari)

        val navController = findNavController(R.id.nav_host_fragment_content_assignatures)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.m6uf1, R.id.m6uf2, R.id.m6uf3, R.id.m6uf4,
                R.id.m7uf1, R.id.m7uf2, R.id.m8uf1, R.id.m8uf2, R.id.m8uf3,
                R.id.m9uf1, R.id.m9uf2, R.id.m9uf3, R.id.m10uf1, R.id.m10uf2,
                R.id.m13uf1
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.assignatures, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val bundle = Bundle()
        bundle.putString("nomusuari", nomusuari)
        val navController = findNavController(R.id.nav_host_fragment_content_assignatures)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_url){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://moodle.iescarlesvallbona.cat/login/index.php")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp()
    }
}