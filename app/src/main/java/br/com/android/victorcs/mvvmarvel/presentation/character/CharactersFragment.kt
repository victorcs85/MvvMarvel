package br.com.android.victorcs.mvvmarvel.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.android.victorcs.mvvmarvel.R
import br.com.android.victorcs.mvvmarvel.domain.model.Character
import dagger.hilt.android.AndroidEntryPoint

private const val NUMBER_COLUMNS = 2
const val CHARACTER_URL_KEY = "mvvmarvel_character_url"
const val CHARACTER_NAME_KEY = "mvvmarvel_character_name"

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    //region Views
    private var rvCharacters: RecyclerView? = null
    //endregion

    private val viewModel: CharactersViewModel by viewModels()

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

    private fun loadCharacters() =
        viewModel.loadCharacters()

    private fun setupViewModel() =
        viewModel.character.observe(viewLifecycleOwner) { characters ->
            characterAdapter.submitList(characters)
        }

    private fun initComponents() {
        rvCharacters = activity?.findViewById(R.id.rv_characters) as? RecyclerView
        rvCharacters?.apply {
            layoutManager = GridLayoutManager(context, NUMBER_COLUMNS)
            adapter = characterAdapter
        }
    }

    private fun onClickCharacter(character: Character) {
        val characterUrl = viewModel.getCharacterUrl(character)
        findNavController().run {
            if (currentDestination?.id == R.id.charactersFragment) {
                navigate(
                    R.id.action_departmentListFragment_to_departmentDetailFragment,
                    bundleOf(
                        CHARACTER_URL_KEY to characterUrl,
                        CHARACTER_NAME_KEY to character.name
                    )
                )
            }
        }
    }

    @Composable
    fun setupCharacterView() {
    }
}