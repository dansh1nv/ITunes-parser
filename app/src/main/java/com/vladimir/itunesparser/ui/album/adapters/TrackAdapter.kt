package com.vladimir.itunesparser.ui.album.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladimir.itunesparser.R
import com.vladimir.itunesparser.data.models.AlbumDetail
import com.vladimir.itunesparser.data.models.Track
import com.vladimir.itunesparser.databinding.ListItemTrackBinding

class TrackAdapter(var tracks: List<Track>) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemTrackBinding>(inflater, R.layout.list_item_track, parent, false)
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    override fun getItemCount(): Int = tracks.size

    class TrackViewHolder(private val binding: ListItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(track: Track){
            binding.track = track
        }
    }
}