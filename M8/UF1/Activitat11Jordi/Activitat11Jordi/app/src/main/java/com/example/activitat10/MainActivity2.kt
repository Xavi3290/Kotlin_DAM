package com.example.activitat10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.activitat10.API.APIService
import com.example.activitat10.API.CrudApi
import com.example.activitat10.API.HeaderInterceptor
import com.example.activitat10.API.logging
import com.example.activitat10.database.ProducteCarret
import com.example.activitat10.database.database
import com.example.activitat10.databinding.ActivityMain2Binding
import com.example.activitat10.notification.GlideApp
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext

class MainActivity2 : AppCompatActivity(), CoroutineScope {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding
    private lateinit var db: database
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        omplirDadesPortada()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        runBlocking {
            val crudApi = CrudApi()
            val corrutina = launch {
                llistaProductes = crudApi.getAllFromAPI()
            }
            corrutina.join()
        }

        omplirCarretBD()

        // emetre notificacions
        val prods = llistaProductes.filter {
                producte -> producte.tipusCardview!!.equals(3)
        } as ArrayList<Producte>

        var requestCode = 0
        for (prod in prods) {
            createNotification(prod.codi!!, "Producte en oferta", prod.descripcio, prod.imatge, requestCode)
            requestCode++
        }

        usuariActual = intent.getStringExtra("nomusu").toString()
        tipusUsuari = intent.getStringExtra("tipus").toString()

        binding = ActivityMain2Binding.inflate(layoutInflater)

        if (tipusUsuari == "Venedor"){
            binding.appBarMain.fab.visibility = View.GONE
        }

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
                R.id.nav_ini, R.id.computers, R.id.mobility, R.id.smartphones, R.id.tv, R.id.afegirProducte, R.id.modificarProducte, R.id.esborrarProducte
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.getHeaderView(0).findViewById<TextView>(R.id.nomUsu).setText(usuariActual)

        if (tipusUsuari=="Venedor") {
            navView.menu.findItem(R.id.menumanteniment).isVisible = true
        }


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

    private fun createNotification(
        codiP : Int,
        title: String,
        content: String,
        imageUrl : String,
        requestCode : Int
    ) {

        val channedId = getString(R.string.channel_id)

        // Configura la resposta a la polsació sobre la notificació
        val notifyIntent = Intent(this, FitxaProducte::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        notifyIntent.putExtra("codiproducte", codiP)

        val notifyPendingIntent = PendingIntent.getActivity(
            this, requestCode, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(this, channedId)
            .setSmallIcon(R.drawable.logopetit)
            .setAutoCancel(true)
            .setLights(Color.BLUE, 500, 500)
            .setVibrate(longArrayOf(500, 500, 500))
            .setContentIntent(notifyPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(title)
            .setContentText(content)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        // Since android Oreo notification channel is needed.
        val notificationManager = NotificationManagerCompat.from(this)

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channedId,
                channedId,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(channel)
        }

        GlideApp.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    //largeIcon
                    notificationBuilder.setLargeIcon(resource)
                    //Big Picture
                    notificationBuilder.setStyle(
                        NotificationCompat.BigPictureStyle().bigPicture(resource)
                    )
                    val notification = notificationBuilder.build()
                    notificationManager.notify(NotificationID.iD, notification)
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })

    }

    internal object NotificationID {
        private val c = AtomicInteger(100)
        val iD: Int
            get() = c.incrementAndGet()
    }


}