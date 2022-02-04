package br.com.android.victorcs.mvvmarvel.presentation.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import br.com.android.victorcs.mvvmarvel.R

class CharacterDetailsFragment : Fragment() {

    //region Views
    private var webView: WebView? = null
    //endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        loadCharacterDetailUrl()
    }

    private fun initComponents() {
        webView = activity?.findViewById(R.id.web_view)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadCharacterDetailUrl() {
        arguments?.getString(CHARACTER_URL_KEY)?.let { characterUrl ->

            webView?.apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        view?.loadUrl(url ?: "")
                        return true
                    }
                }
                loadUrl(characterUrl)
            }

        }
    }
}