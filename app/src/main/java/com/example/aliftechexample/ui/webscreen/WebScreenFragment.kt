package com.example.aliftechexample.ui.webscreen

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.example.aliftechexample.R
import kotlinx.android.synthetic.main.fragment_web_screen.*

class WebScreenFragment : Fragment() {

    companion object {
        const val URL = "URL"
        fun getInstance(url: String): WebScreenFragment {
            val bundle = Bundle()
            bundle.putString(URL, url)
            return WebScreenFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView.settings.javaScriptEnabled = true
        arguments?.let {
            val url = it.getString(URL)
            if (url != null) {
                webView.loadUrl("https://guidebook.com")
            }
        }
        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pbLoading?.isGone = true
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }
        }


    }
}