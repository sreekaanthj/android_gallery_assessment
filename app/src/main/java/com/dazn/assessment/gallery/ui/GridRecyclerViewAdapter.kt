package com.dazn.assessment.gallery.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.dazn.assessment.gallery.R
import com.dazn.assessment.gallery.data.model.ImageInfo
import com.dazn.assessment.gallery.databinding.ItemGridImageBinding

class GridRecyclerViewAdapter(private val images: List<ImageInfo>) :
    RecyclerView.Adapter<GridRecyclerViewAdapter.GridItemViewHolder>() {

    private val TAG = "GridRecyclerViewAdapter"

    class GridItemViewHolder(
        private val binding: ItemGridImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ImageInfo) {
            Log.i("GridRecyclerViewAdapter", "bind: loading image:: ${data.title}")
            binding.sampleTxt.text = data.date

            binding.gridImg.load(data.url) {
                crossfade(true)
                placeholder(R.drawable.baseline_downloading_24)
                //transformations(RoundedCornersTransformation(8.0F))
            }
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