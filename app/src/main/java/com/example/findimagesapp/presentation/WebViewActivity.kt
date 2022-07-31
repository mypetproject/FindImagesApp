package com.example.findimagesapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.ActivityWebviewBinding

/**
 * Screen for web view display functionality
 *
 * @author S. Kishkar
 */
class WebViewActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityWebviewBinding>(this, R.layout.activity_webview)
            .let {
                it.webview.run {
                    webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            super.onProgressChanged(view, newProgress)
                            if (progress == MAX_PROGRESS) {
                                it.progressCircular.isGone = true
                                it.webview.isVisible = true
                            }
                        }
                    }
                    loadUrl(intent.getStringExtra(URL).orEmpty())
                    settings.javaScriptEnabled = true
                }
            }
    }

    companion object {
        /**
         * Tag for extra string with URL
         */
        const val URL = "URL"
        private const val MAX_PROGRESS = 100
    }
}