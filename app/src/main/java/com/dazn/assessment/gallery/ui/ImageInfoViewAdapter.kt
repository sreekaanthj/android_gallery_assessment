package com.dazn.assessment.gallery.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dazn.assessment.gallery.R
import com.dazn.assessment.gallery.data.model.ImageInfo
import com.dazn.assessment.gallery.data.model.fullDetails
import com.dazn.assessment.gallery.databinding.ItemImageDetailsBinding
import timber.log.Timber

class ImageInfoViewAdapter(private val imageInfos: List<ImageInfo>) :
    RecyclerView.Adapter<ImageInfoViewAdapter.ImageInfoViewHolder>() {


    class ImageInfoViewHolder(private val binding: ItemImageDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ImageInfo) {

            Timber.v("bind:: ${data.date} .. fullDetailsLength: ${data.fullDetails().length}")

            binding.tvImageInfo.text = data.fullDetails()

            binding.hdImg.load(data.hdurl) {
                crossfade(true)
                placeholder(R.drawable.baseline_downloading_24)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageInfoViewHolder {
        val binding =
            ItemImageDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageInfoViewHolder(binding)
    }

    override fun getItemCount(): Int = imageInfos.size

    override fun onBindViewHolder(holder: ImageInfoViewHolder, position: Int) {
        holder.bind(imageInfos[position])
    }


}