package com.vladimir.itunesparser.ui.album.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vladimir.itunesparser.R
import com.vladimir.itunesparser.data.models.AlbumDetail

class TrackViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_track, parent, false)) {
    private var mTrackNumber: TextView? = null
    private var mTrackName: TextView? = null
    private var mArtistName: TextView? = null

    init {
        mTrackNumber = itemView.findViewById(R.id.item_track_number)
        mTrackName = itemView.findViewById(R.id.item_track_name)
        mArtistName = itemView.findViewById(R.id.artist_name)
    }

    fun bind(albumDetail: AlbumDetail){
        mTrackNumber?.text = albumDetail.trackNumber.toString()
        mTrackName?.text = albumDetail.trackName
        mArtistName?.text = albumDetail.artistName
    }

}