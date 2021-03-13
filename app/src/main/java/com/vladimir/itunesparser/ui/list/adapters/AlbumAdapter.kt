 package com.vladimir.itunesparser.ui.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vladimir.itunesparser.R
import com.vladimir.itunesparser.data.models.Album
import com.vladimir.itunesparser.databinding.ListItemAlbumBinding

 class AlbumAdapter(var partAlbumList: List<Album>, private val clickListener: (Album) -> Unit) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemAlbumBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_album, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(partAlbumList[position], clickListener)
    }

    override fun getItemCount(): Int = partAlbumList.size

     class AlbumViewHolder(private val binding: ListItemAlbumBinding) : RecyclerView.ViewHolder(binding.root){

         private var mArtwork: ImageView? = null

         init {
             mArtwork = itemView.findViewById(R.id.artwork)
         }

         fun bind(album: Album, clickListener: (Album) -> Unit ) {
             binding.album = album
             Picasso.get()
                 .load(album.artworkUrl)
                 .into(mArtwork)
             itemView.setOnClickListener { clickListener(album)}
         }
     }
}