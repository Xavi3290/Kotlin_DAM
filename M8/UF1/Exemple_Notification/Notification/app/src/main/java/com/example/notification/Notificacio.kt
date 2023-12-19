package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import androidx.media.app.NotificationCompat as MediaNotificationCompat

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
            notify(nNotificacio,notificacio)
        }
        nNotificacio++
    }

    fun generaNoticacioText(titol: String, contingut: String, textllarg: String) {
        // Agafa una icona per mostrar amb la notificació
        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.escut_icon)

        // Configura la resposta a la polsació sobre la notificació
        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notifyPendingIntent = PendingIntent.getActivity(
            context, 0, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Construeix la notificació
        val notificacio = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_icona)
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
    fun generaNoticacioImatge(titol: String, contingut: String, imatge: Int) {
        // Agafa una icona per mostrar amb la notificació
        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.escut_icon)
        val imatgeResource = BitmapFactory.decodeResource(context.resources, R.drawable.cvlogo)

        // Configura la resposta a la polsació sobre la notificació
        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notifyPendingIntent = PendingIntent.getActivity(
            context, 0, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Construeix la notificació
        val notificacio = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_icona)
            .setContentTitle(titol)
            .setContentText(contingut)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(imatgeResource)
                    .bigLargeIcon(null)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(notifyPendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .build()

        mostraNotificacio(notificacio)
    }

    fun generaNotificacioMedia(autor: String, canco: String) {
        val albumCoverBitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.album_cover
        )
        val mediaStyle = MediaNotificationCompat.MediaStyle()
            .setShowActionsInCompactView(1, 2, 3)

        val notificacio = NotificationCompat.Builder(context, channelId)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_icona)

            .addAction(android.R.drawable.ic_media_previous, "Anterior", null)
            .addAction(android.R.drawable.ic_media_rew, "Principi", null)
            .addAction(android.R.drawable.ic_media_pause, "Pausa", null)
            .addAction(android.R.drawable.ic_media_ff, "Final", null)
            .addAction(android.R.drawable.ic_media_next, "Següent", null)

            .setStyle(mediaStyle)
            .setContentTitle(autor)
            .setContentText(canco)
            .setLargeIcon(albumCoverBitmap)
            .build()

        mostraNotificacio(notificacio)
    }

    fun generaNotificacioInbox(dades: ArrayList<String>){
        val inboxStyle = NotificationCompat.InboxStyle()
        var cont = 0
        for (dada in dades){
            inboxStyle.addLine(dada)
            cont++
        }

        val notificacio = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_icona)
            .setContentTitle("Tens $cont notificacions")
            .setStyle(inboxStyle) // (3)
            .build()

        with(NotificationManagerCompat.from(context, )){
            notify(nNotificacio, notificacio)
        }
        nNotificacio++
    }

    fun generaNotificacioProgress(titol: String) {
        val notificacio = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_icona)
            .setContentTitle(titol)
            .setProgress(0, 0, true)
            .addAction(0, "Cancel·lar", null)
            .build()

        mostraNotificacio(notificacio)
    }
}