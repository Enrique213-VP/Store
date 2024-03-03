package com.svape.store.iu.Fragments.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.svape.store.R
import com.svape.store.databinding.FragmentProductBinding
import com.svape.store.databinding.FragmentProductDetailBinding
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.FragmentComponent

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding: FragmentProductDetailBinding
    private val args by navArgs<ProductDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
        Glide.with(requireContext()).load(args.productImageUrl).centerCrop()
            .into(binding.imgBackground)
        binding.txtProductTitle.text = args.title
        binding.txtDescription.text = args.description
        binding.txtRating.text = "Valoración ${args.rating}"
        binding.txtPrice.text = args.price.toString()
    }
}