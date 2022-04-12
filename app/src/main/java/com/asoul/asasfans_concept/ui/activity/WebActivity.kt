package com.asoul.asasfans_concept.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.webkit.WebViewFeature
import com.asoul.asasfans_concept.R
import com.asoul.asasfans_concept.databinding.ActivityWebBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/08 008 20:35
 * @Description : Description...
 */
class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    private lateinit var url: String
    private var lastUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.webToolbar)
        url = intent.getStringExtra("web_url") ?: ""
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setTitle(R.string.loading)
            it.subtitle = url
        }
        initWebViewSettings()
        loadWeb()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_web, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.web_refresh -> loadWeb()
            R.id.open_in_browser -> {
                val uri = Uri.parse(lastUrl ?: url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
        return true
    }

    private val ownWebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            binding.webProgressBar.isGone = false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            binding.webProgressBar.isGone = true
            supportActionBar?.subtitle = url
            lastUrl = url
        }
    }

    private val ownWebChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            supportActionBar?.title = title
        }

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            binding.webProgressBar.progress = newProgress
        }

        override fun onJsAlert(
            view: WebView?,
            url: String?,
            message: String?,
            result: JsResult?
        ): Boolean {
            MaterialAlertDialogBuilder(binding.webView.context)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .show()
            return true
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        binding.webView.apply {
            if (WebViewFeature.isFeatureSupported(WebViewFeature.GET_WEB_VIEW_CLIENT)) {
                webViewClient = ownWebViewClient
            }
            if (WebViewFeature.isFeatureSupported(WebViewFeature.GET_WEB_CHROME_CLIENT)) {
                webChromeClient = ownWebChromeClient
            }
        }

        val webSettings = binding.webView.settings
        webSettings.apply {
            setSupportZoom(true)
            databaseEnabled = true
            javaScriptEnabled = true
            domStorageEnabled = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            useWideViewPort = true
            blockNetworkImage = false
            databaseEnabled = true
            loadWithOverviewMode = true
            loadsImagesAutomatically = true
            builtInZoomControls = true
            displayZoomControls = false
            javaScriptCanOpenWindowsAutomatically = true
        }
    }

    private fun loadWeb() {
        binding.webView.loadUrl(url)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (binding.webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}