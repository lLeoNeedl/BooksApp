package com.example.booksapp.presentation.web_page_screen

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageScreen(url: String) {
    AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
        val webView = WebView(context)
        webView.webViewClient = (object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        })
        webView.loadUrl(url)
        webView.settings.javaScriptEnabled = true
        webView
    })
}