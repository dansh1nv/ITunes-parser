package com.vladimir.itunesparser.ui.album

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.vladimir.itunesparser.R
import com.vladimir.itunesparser.data.models.AlbumDetail
import com.vladimir.itunesparser.data.models.Track
import com.vladimir.itunesparser.databinding.FragmentDetailBinding
import com.vladimir.itunesparser.platform.BaseFragment
import com.vladimir.itunesparser.ui.album.adapters.TrackAdapter
import com.vladimir.itunesparser.utils.viewBinding
import com.vladimir.testtask.utils.Constants

class AlbumDetailFragment : BaseFragment(R.layout.fragment_detail) {

    private val binding by viewBinding (FragmentDetailBinding::bind)

    private val detailViewModel: AlbumDetailViewModel by lazy {
        ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObservers()
    }

    private fun initialize(){
        val query = activity?.intent?.getStringExtra(Constants.COLLECTION_ID).orEmpty()
        detailViewModel.fetchAlbumDetail(query)
    }

    private fun setupObservers() {
        detailViewModel.trackLiveData.observe(viewLifecycleOwner, {
            setupTracksRv(it)
        })
        detailViewModel.albumDetailLiveData.observe(viewLifecycleOwner, {
            val album = it[0]
            setupAlbumDetail(album)
        })
    }

    private fun setupTracksRv(tracks: List<Track>) {
        binding.trackList.apply {
            layoutManager =  LinearLayoutManager(activity)
            adapter = TrackAdapter(tracks)
        }
    }

    private fun setupAlbumDetail(albumDetail: AlbumDetail) {
        binding.apply {
            albumName.text = albumDetail.albumName
            artistName.text = albumDetail.artistName
            genreName.text = albumDetail.genreName
            copyright.text = albumDetail.copyright
        }

        Picasso.get()
            .load(albumDetail.artworkUrl)
            .into(binding.artwork)
    }

    companion object {
        fun newInstance(): AlbumDetailFragment = AlbumDetailFragment()
    }
}
