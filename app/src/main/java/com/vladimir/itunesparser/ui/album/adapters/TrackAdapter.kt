package com.vladimir.itunesparser.ui.album.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladimir.itunesparser.data.models.AlbumDetail

class TrackAdapter(var partAlbumDetailList: List<AlbumDetail>) : RecyclerView.Adapter<TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrackViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(partAlbumDetailList[position])
    }

    override fun getItemCount(): Int = partAlbumDetailList.size

}