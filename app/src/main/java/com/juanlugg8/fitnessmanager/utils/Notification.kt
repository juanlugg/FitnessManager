package com.juanlugg8.fitnessmanager.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.juanlugg8.fitnessmanager.MainActivity

@RequiresApi(Build.VERSION_CODES.O)
class Notification {
    companion object{
        fun showNotification(context: Context, title: String, message: String, channel: NotificationChannel, channel_id : String, notification_id:Int,) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            notificationManager.notify(notification_id, builder.build())
        }
        fun showNotificationWithNav(context: Context, title: String, message: String, channel: NotificationChannel, channel_id : String, notification_id:Int, navId : Int) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
            val pendingIntent = NavDeepLinkBuilder(context)
                .setComponentName(MainActivity::class.java)
                .setGraph(com.juanlugg8.fitnessmanager.R.navigation.nav_graph)
                .setDestination(navId)
                .createPendingIntent()
            val builder = NotificationCompat.Builder(context, channel_id)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            notificationManager.notify(notification_id, builder.build())
        }
    }
}