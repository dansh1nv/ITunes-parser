package com.vladimir.itunesparser.ui.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vladimir.itunesparser.R
import com.vladimir.itunesparser.data.models.Album

class AlbumViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_album, parent, false)){

    private var mAlbumName: TextView? = null
    private var mArtistName: TextView? = null
    private var mArtwork: ImageView? = null

    init {
        mAlbumName = itemView.findViewById<View>(R.id.item_album_title) as TextView
        mArtistName = itemView.findViewById<View>(R.id.item_album_artist) as TextView
        mArtwork = itemView.findViewById(R.id.item_album_artwork)
    }

    fun bind(album: Album, clickListener: (Album) -> Unit ) {
        mAlbumName?.text = album.albumName
        mArtistName?.text = album.artistName
        Picasso.get()
            .load(album.artworkUrl)
            .into(mArtwork)
        itemView.setOnClickListener { clickListener(album)}
    }

}