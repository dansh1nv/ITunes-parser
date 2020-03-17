package com.vladimir.itunesparser.ui.album

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.vladimir.itunesparser.R
import com.vladimir.itunesparser.data.models.AlbumDetail
import com.vladimir.itunesparser.platform.BaseFragment
import com.vladimir.itunesparser.ui.album.adapters.TrackAdapter
import com.vladimir.testtask.utils.Constants
import kotlinx.android.synthetic.main.fragment_detail.*

class AlbumDetailFragment : BaseFragment(R.layout.fragment_detail) {

    private var mAlbumName: TextView? = null
    private var mArtistName: TextView? = null
    private var mGenre: TextView? = null
    private var mArtwork: ImageView? = null
    private var mCopyright: TextView? = null

    private val detailViewModel: AlbumDetailViewModel by lazy {
        ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObservers()
    }

    private fun initialize(){
        val query = activity?.intent?.getIntExtra(Constants.COLLECTION_ID, -1).toString()
        detailViewModel.fetchAlbumDetail(query)
        mAlbumName = activity?.findViewById(R.id.album_name)
        mArtistName = activity?.findViewById(R.id.artist_name)
        mGenre = activity?.findViewById(R.id.genre_name)
        mArtwork = activity?.findViewById(R.id.artwork)
        mCopyright = activity?.findViewById(R.id.copyright)
    }

    private fun setupObservers() {
        detailViewModel.trackLiveData.observe(viewLifecycleOwner, Observer {
            setupTracksRv(it)
        })
        detailViewModel.albumDetailLiveData.observe(viewLifecycleOwner, Observer {
            setupAlbumDetail(it)
        })
    }

    private fun setupTracksRv(albumDetails: List<AlbumDetail>) {
        track_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TrackAdapter(albumDetails)
        }
    }

    private fun setupAlbumDetail(albumDetails: List<AlbumDetail>) {
        val albumDetail = albumDetails[0]
        mAlbumName?.text = albumDetail.albumName
        mArtistName?.text = albumDetail.artistName
        mGenre?.text = albumDetail.genreName
        Picasso.get()
            .load(albumDetail.artworkUrl)
            .into(mArtwork)
        mCopyright?.text = albumDetail.copyright
    }

    companion object {
        fun newInstance(): AlbumDetailFragment = AlbumDetailFragment()
    }
}
