package com.dazn.assessment.gallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dazn.assessment.gallery.data.model.ImageInfo
import com.dazn.assessment.gallery.databinding.FragmentGridBinding
import com.dazn.assessment.gallery.ui.GalleryViewModel
import com.dazn.assessment.gallery.ui.GridRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class GridFragment : Fragment() {
    private val TAG = "GridFragment"

    private var _binding: FragmentGridBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val galleryViewModel: GalleryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGridBinding.inflate(inflater, container, false)
        return binding.root

    }


    fun onGridItemClicked(imageInfo: ImageInfo) {
        val selectedIndex = galleryViewModel.galleryImages.value?.indexOf(imageInfo) ?: 0

        val actionGridFragmentToImageDetailsFragment =
            GridFragmentDirections.actionGridFragmentToImageDetailsFragment(
                selectedIndex
            )

        _binding?.root?.findNavController()?.navigate(actionGridFragmentToImageDetailsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.v("GridFragment onViewCreated Called...")

        galleryViewModel.galleryImages.observe(viewLifecycleOwner) {
            _binding?.imagesGridRv?.adapter = it?.let { it1 ->
                GridRecyclerViewAdapter(it1,
                    object : GridRecyclerViewAdapter.GridItemClickListener {
                        override fun onGridItemClick(item: ImageInfo) {
                            Log.i(TAG, "onGridItemClick: item clicked ${item.date}")
                            onGridItemClicked(item)
                        }
                    }
                )
            }
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_GridFragment_to_ImageDetailsFragment)
        }

        galleryViewModel.loadGallery()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}