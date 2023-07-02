package com.dazn.assessment.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dazn.assessment.gallery.databinding.FragmentImageDetailsBinding
import com.dazn.assessment.gallery.ui.GalleryViewModel
import com.dazn.assessment.gallery.ui.ImageInfoViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    private var _binding: FragmentImageDetailsBinding? = null

    val args: ImageDetailsFragmentArgs by navArgs()

    val galleryViewModel: GalleryViewModel by activityViewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Timber.v("selected image index :: ${args.selectedImageIndex}")

        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_ImageDetailsFragment_to_GridFragment)
        }

        galleryViewModel.galleryImages.observe(viewLifecycleOwner) {
            Timber.v("load images in viewpager2")

            _binding?.vpImageInfo?.adapter = it?.let { it1 -> ImageInfoViewAdapter(it1) }

            _binding?.vpImageInfo?.setCurrentItem(args.selectedImageIndex, false)

            _binding?.vpImageInfo?.offscreenPageLimit = 1


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}