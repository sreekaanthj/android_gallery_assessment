package com.dazn.assessment.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dazn.assessment.gallery.databinding.FragmentGridBinding
import com.dazn.assessment.gallery.ui.GalleryViewModel
import com.dazn.assessment.gallery.ui.GridRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class GridFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        galleryViewModel.galleryImages.observe(viewLifecycleOwner) {
            _binding?.imagesGridRv?.adapter = it?.let { it1 -> GridRecyclerViewAdapter(it1) }
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