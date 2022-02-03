package br.com.android.victorcs.mvvmarvel.presentation.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.android.victorcs.mvvmarvel.R
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import timber.log.Timber

class CharactersAdapter(
    private val clickListener: (character: Character) -> Unit
) : ListAdapter<Character, CharactersAdapter.CharacterViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_character, parent, false)
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bindView(getItem(position))

    class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class CharacterViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val characterName: AppCompatTextView = itemView.findViewById(R.id.actv_character_name)
        private val characterImage: AppCompatImageView = itemView.findViewById(R.id.aciv_item_character_image)

        fun bindView(character: Character) {
            characterName.text = character.name
            try {
                Glide.with(itemView.context)
                    .load(character.thumbnail?.pathMedium.orEmpty())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(characterImage)
            } catch (ex: Exception) {
                Timber.e(ex)
            }

            itemView.setOnClickListener {
                clickListener(character)
            }
        }
    }
}