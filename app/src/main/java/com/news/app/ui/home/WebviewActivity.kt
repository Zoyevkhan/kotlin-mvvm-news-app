package com.news.app.ui.home

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.news.app.R
import com.news.app.databinding.ActivityWebviewBinding


import android.webkit.WebView

import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import com.news.app.utils.extensions.setVisible


class WebviewActivity : AppCompatActivity() {
    lateinit var url: String
    lateinit var binding: ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData();
        with(binding.webview) {
            webViewClient = MyWBClient()
            settings.javaScriptEnabled = true
        }
        loadURL()
    }

    private fun loadURL() {
        binding.webview.loadUrl(url)
    }

    private fun getIntentData() {
        intent.extras?.let { bundle ->
            bundle.getString("URL")?.let { url_data ->
                url = url_data
            }


        }
    }

  inner  class MyWBClient : android.webkit.WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            view.loadUrl(url!!)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progress.setVisible(false)
            binding.webview.setVisible(true)

        }
    }

    override fun onBackPressed() {
        if(binding.webview.canGoBack()){
            binding.webview.goBack()
        }else{
            super.onBackPressed()
        }
    }
}