package com.vladimir.itunesparser.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladimir.itunesparser.R
import com.vladimir.itunesparser.data.models.Album
import com.vladimir.itunesparser.platform.BaseFragment
import com.vladimir.itunesparser.ui.album.AlbumDetailActivity
import com.vladimir.itunesparser.ui.list.adapters.AlbumAdapter
import com.vladimir.testtask.utils.Constants
import kotlinx.android.synthetic.main.fragment_album_list.*


class AlbumListFragment : BaseFragment(R.layout.fragment_album_list) {

    private val viewModel: AlbumListViewModel by lazy {
        ViewModelProviders.of(this).get(AlbumListViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                viewModel.fetchAlbums(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
            return false
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.albumLiveData.observe(this, Observer {
            setupAlbumsRv(it)
        })
    }

    private fun setupAlbumsRv(albums: List<Album>) {
        albums.sortedWith(compareBy { it.albumName })
        album_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter =
                AlbumAdapter(albums) { albumItem: Album ->
                    setupListeners(albumItem)
                }
        }
    }

    private fun setupListeners(albumItem: Album) {
        val intent = Intent(activity, AlbumDetailActivity::class.java)
        val value = albumItem.collectionId
        intent.putExtra(Constants.COLLECTION_ID, value)
        startActivity(intent)
    }

    companion object {
        fun newInstance(): AlbumListFragment = AlbumListFragment()
    }
}