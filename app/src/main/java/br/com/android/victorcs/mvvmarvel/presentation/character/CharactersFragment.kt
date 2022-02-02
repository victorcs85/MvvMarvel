package br.com.android.victorcs.mvvmarvel.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import br.com.android.victorcs.mvvmarvel.R
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment: Fragment() {

    //region Views
    private var rvCharacters: RecyclerView? = null
    //endregion

    private val viewModel: CharactersViewModel by sharedViewModel()

    private val characterAdapter = CharactersAdapter { onClickCharacter(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        setupViewModel()
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModel.loadCharacters()
    }

    private fun setupViewModel() {
        viewModel.character.observe(viewLifecycleOwner) { characters ->
            characterAdapter.submitList(characters)
        }
    }

    private fun initComponents() {
        rvCharacters = activity?.findViewById(R.id.rv_characters) as? RecyclerView
        rvCharacters?.apply {
            isNestedScrollingEnabled = false
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
            adapter = characterAdapter
        }
    }

    private fun onClickCharacter(character: Character) {

    }
}