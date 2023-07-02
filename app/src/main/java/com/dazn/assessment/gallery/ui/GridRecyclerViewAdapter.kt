package com.dazn.assessment.gallery.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dazn.assessment.gallery.R
import com.dazn.assessment.gallery.data.model.ImageInfo
import com.dazn.assessment.gallery.databinding.ItemGridImageBinding
import timber.log.Timber

class GridRecyclerViewAdapter(
    private val images: List<ImageInfo>,
    private val gridItemClickListener: GridItemClickListener
) :
    RecyclerView.Adapter<GridRecyclerViewAdapter.GridItemViewHolder>() {

    private val TAG = "GridRecyclerViewAdapter"

    interface GridItemClickListener {
        fun onGridItemClick(item: ImageInfo)
    }

    class GridItemViewHolder(
        private val binding: ItemGridImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ImageInfo, gridItemClickListener: GridItemClickListener) {
            Timber.v("bind: loading image:: ${data.title}")
            binding.sampleTxt.text = data.date

            binding.gridImg.load(data.url) {
                crossfade(true)
                placeholder(R.drawable.baseline_downloading_24)
                //transformations(RoundedCornersTransformation(8.0F))
            }

            binding.root.setOnClickListener { gridItemClickListener.onGridItemClick(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val binding =
            ItemGridImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridItemViewHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(images[position], gridItemClickListener)
    }
}