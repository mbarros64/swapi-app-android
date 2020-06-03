package com.mbarros64.swapi_app_android.characters.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.mbarros64.swapi_app_android.characters.R
import com.mbarros64.swapi_app_android.characters.search.models.CharacterSearchModel
import com.swapi_app_android.starwars.archieteture.BaseFragment
import com.swapi_app_android.starwars.extensions.EndlessScrollListener
import com.swapi_app_android.starwars.extensions.visible
import com.swapi_app_android.starwars.extensions.gone
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.actionbar_toolbar.*
import kotlinx.android.synthetic.main.fragment_search_character.*
import org.koin.android.ext.android.inject
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.actionbar_toolbar.*
import java.util.concurrent.TimeUnit


class CharacterSearchFragment : BaseFragment(), CharacterSearchAdapter.Interaction {

    override val layout = R.layout.fragment_search_character

    override val viewModel: CharacterSearchVM by inject()

    private val adapter: CharacterSearchAdapter by lazy { CharacterSearchAdapter(this) }

    //Search
    private var searchView: android.widget.SearchView? = null
    private val searchListener = PublishSubject.create<String>()

    //Pagination
    private lateinit var endlessScrollListener: EndlessScrollListener

    companion object {
        const val TAG = "CharacterSearchFragment"
        fun newInstance() = CharacterSearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar(toolbar)

        rvCharacters.setHasFixedSize(true)
        rvCharacters.adapter = adapter

        srlCharacters.setOnRefreshListener {
            refreshCharacters()
        }

        btnShowAllCharacters.setOnClickListener {
            refreshCharacters()
        }

        startListeningToPaginationLoading()

        startListeningToCharacters()

        initSearchBehaviour()

        viewModel.initialLoad()

        endlessScrollListener = initEndlessScroll()

    }

    private fun refreshCharacters() {
        llNoData.gone()
        searchView?.setQuery("", false)
        searchView?.clearFocus()
        viewModel.refreshCharacters()
    }

    private fun startListeningToPaginationLoading() {
        viewModel.paginationLoading.observe(this, Observer {
            if (it) {
                pbLoading.visible()
            } else pbLoading.gone()
        })
    }

    private fun initSearchBehaviour() {
        //A listener to get user's query and manipulate it before going to vm
        searchListener
            //To ensure queries are run when the user pauses typing
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinct()
            .subscribe {
                //Reset the pagination state
                endlessScrollListener.resetState()
                viewModel.searchCharacter(it)
            }
            .addTo(viewModel.disposable)
    }

    private fun startListeningToCharacters() {
        viewModel.characters.observe(this, Observer { characters ->
            if (characters.isEmpty()) {
                llNoData.visible()
                rvCharacters.gone()
            } else {
                adapter.swapData(characters)
                llNoData.gone()
                rvCharacters.visible()
            }
        })
    }

    private fun initEndlessScroll() = object : EndlessScrollListener(
        layoutManager = rvCharacters.layoutManager as LinearLayoutManager,
        visibleThreshold = 2) {
        //This will be called each time the user scrolls
        // and only 2 elements are left in the recyclerview items.
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
            viewModel.loadNextPage()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)
        val myActionMenuItem = menu.findItem(R.id.item_search)
        searchView = myActionMenuItem.actionView as SearchView
        searchView?.queryHint = getString(R.string.enter_character_name)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchListener.onNext(newText)
                return false
            }
        })
    }

    override fun hideLoading() {
        srlCharacters.isRefreshing = false
    }

    override fun showLoading() {
        srlCharacters.isRefreshing = true
    }

    override fun onResume() {
        super.onResume()
        rvCharacters.addOnScrollListener(endlessScrollListener)
    }

    //Adapter interactions
    override fun characterClicked(character: CharacterSearchModel) {
        showToast("Clicked $character")
    }

}