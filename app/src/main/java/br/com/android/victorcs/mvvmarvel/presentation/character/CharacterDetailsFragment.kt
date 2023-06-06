package br.com.android.victorcs.mvvmarvel.presentation.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import br.com.android.victorcs.mvvmarvel.R
import br.com.android.victorcs.mvvmarvel.presentation.utils.WebViewClientHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    private fun loadCharacterDetailUrl() =
        arguments?.getString(CHARACTER_URL_KEY)?.let { characterUrl ->
            webView?.apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClientHandler()
                loadUrl(characterUrl)
            }
        }
}