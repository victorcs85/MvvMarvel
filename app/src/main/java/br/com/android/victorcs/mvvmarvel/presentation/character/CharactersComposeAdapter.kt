package br.com.android.victorcs.mvvmarvel.presentation.character

import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.accompanist.themeadapter.material.MdcTheme

class CharactersComposeAdapter : RecyclerView.Adapter<MyComposeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyComposeViewHolder {
        return MyComposeViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: MyComposeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    // NOTE: No need to explicitly call .disposeComposition()
}

class MyComposeViewHolder(
    val composeView: ComposeView
) : ViewHolder(composeView) {
    fun bind(input: String) {
        composeView.setContent {
            MdcTheme {
                Text(input)
            }
        }
    }
}