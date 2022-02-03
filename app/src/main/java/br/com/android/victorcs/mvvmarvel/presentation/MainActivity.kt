package br.com.android.victorcs.mvvmarvel.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import br.com.android.victorcs.mvvmarvel.R
import br.com.android.victorcs.mvvmarvel.presentation.character.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    //region Views
    private var loadingView: ViewGroup? = null
    private var container: FragmentContainerView? = null
    //endregion

    private lateinit var navController: NavController
    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavController()
        setupViews()
//        initViewModel()
    }

    private fun initViewModel() {
        viewModel.loading.observe(this) { isShow ->
            if (isShow) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    private fun setupViews() {
        loadingView = findViewById(R.id.view_loading) as? ViewGroup
        container = findViewById(R.id.fragment_container) as? FragmentContainerView
    }

    private fun showLoading() {
        loadingView?.visibility = View.VISIBLE
        container?.visibility = View.GONE

    }

    private fun hideLoading() {
        loadingView?.visibility = View.GONE
        loadingView?.visibility = View.VISIBLE
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val graph =
            navHostFragment.navController.navInflater.inflate(R.navigation.marvel_navigation)

        navHostFragment.navController.graph = graph
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}
