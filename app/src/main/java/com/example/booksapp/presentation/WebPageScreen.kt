package com.example.booksapp.presentation

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebPage(url: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val webView = WebView(context)
            webView.loadUrl(url)
//            webView.settings.javaScriptEnabled = true
            webView
        }
    )
}