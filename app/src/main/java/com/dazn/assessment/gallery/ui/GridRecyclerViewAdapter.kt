package com.dazn.assessment.gallery.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dazn.assessment.gallery.data.model.ImageInfo
import com.dazn.assessment.gallery.databinding.ItemGridImageBinding

class GridRecyclerViewAdapter(private val images: List<ImageInfo>) :
    RecyclerView.Adapter<GridRecyclerViewAdapter.GridItemViewHolder>() {

    class GridItemViewHolder(
        private val binding: ItemGridImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ImageInfo) {
            binding.sampleTxt.text = data.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val binding =
            ItemGridImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridItemViewHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(images[position])
    }
}