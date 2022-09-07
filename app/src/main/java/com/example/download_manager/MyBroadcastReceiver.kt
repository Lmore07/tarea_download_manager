package com.example.download_manager

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver(var downloadid: Long): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        if (id == downloadid)
            Toast.makeText(context,  "Download Done!!", Toast.LENGTH_LONG).show()
    }
}