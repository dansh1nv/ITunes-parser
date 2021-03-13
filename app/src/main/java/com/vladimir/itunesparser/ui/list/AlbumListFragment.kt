package com.vladimir.itunesparser.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladimir.itunesparser.R
import com.vladimir.itunesparser.data.models.Album
import com.vladimir.itunesparser.databinding.FragmentAlbumListBinding
import com.vladimir.itunesparser.platform.BaseFragment
import com.vladimir.itunesparser.ui.album.AlbumDetailActivity
import com.vladimir.itunesparser.ui.list.adapters.AlbumAdapter
import com.vladimir.itunesparser.utils.viewBinding
import com.vladimir.testtask.utils.Constants


class AlbumListFragment : BaseFragment(R.layout.fragment_album_list) {

    private val viewModel: AlbumListViewModel by lazy {
        ViewModelProvider(this).get(AlbumListViewModel::class.java)
    }
    private val binding by viewBinding(FragmentAlbumListBinding::bind)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                viewModel.fetchAlbums(query)
                binding.textList.visibility = View.GONE
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
        viewModel.albumLiveData.observe(viewLifecycleOwner, {
            setupAlbumsRv(it)
        })
    }

    private fun setupAlbumsRv(albums: List<Album>) {
        albums.sortedWith(compareBy { it.albumName })
        binding.albumList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = AlbumAdapter(albums) { albumItem: Album ->
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