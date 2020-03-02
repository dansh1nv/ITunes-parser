package com.vladimir.itunesparser.ui.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladimir.itunesparser.data.models.Album

class AlbumAdapter(var partAlbumList: List<Album>, val clickListener: (Album) -> Unit) :
    RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(partAlbumList[position], clickListener)
    }

    override fun getItemCount(): Int = partAlbumList.size
}