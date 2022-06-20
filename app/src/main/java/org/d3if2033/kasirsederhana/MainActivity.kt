package org.d3if2033.kasirsederhana

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3if2033.kasirsederhana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    companion object {
        const val CHANNEL_ID = "updater";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name);
            val importance = NotificationManager.IMPORTANCE_DEFAULT;
            val channel = NotificationChannel(CHANNEL_ID, name, importance);
            channel.description = getString(R.string.channel_description);

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
            manager.createNotificationChannel(channel);

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}