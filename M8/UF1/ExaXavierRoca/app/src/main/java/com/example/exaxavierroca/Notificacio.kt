package com.example.exaxavierroca

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notificacio(val context: Context) {

    var nNotificacio = 1
    val channelId = context.getString(R.string.channel_id)
    init {
        // Necessari en versió 26 (codi O) i superiors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Registra el canal en el sistema
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

    fun mostraNotificacio(notificacio: Notification){
        with(NotificationManagerCompat.from(context, )){
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(nNotificacio,notificacio)
        }
        nNotificacio++
    }

 /*      fun mostraNotificacio1(notificacio: Notification){
           with(NotificationManagerCompat.from(context, )){
               notify(nNotificacio,notificacio)
           }
           nNotificacio++
       }
*/
    fun generaNoticacioText(titol: String, contingut: String, textllarg: String) {
        // Agafa una icona per mostrar amb la notificació
        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.posicion1)

        // Configura la resposta a la polsació sobre la notificació
        val notifyIntent = Intent(context, UsuariActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notifyPendingIntent = PendingIntent.getActivity(
            context, 0, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Construeix la notificació
        val notificacio = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_menu_camera)
            .setLargeIcon(largeIcon)
            .setContentTitle(titol)
            .setContentText(contingut)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(textllarg)

            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(notifyPendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .build()

        mostraNotificacio(notificacio)

    }

}