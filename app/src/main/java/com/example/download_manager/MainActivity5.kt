package com.example.download_manager

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val bundle = this.getIntent().getExtras();
        var link= bundle?.getString("link")

        var webview: WebView =findViewById(R.id.webview);
        webview.webChromeClient=object : WebChromeClient(){}
        webview.webViewClient=object : WebViewClient(){}
        webview.settings.javaScriptEnabled = true
        if (link != null) {
            webview.loadUrl(link)
            webview.WebViewTransport()
        }
    }
}