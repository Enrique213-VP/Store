package com.svape.store.iu.Fragments.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.svape.store.R
import com.svape.store.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding: FragmentProductDetailBinding
    private val args by navArgs<ProductDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
        if (args.stock == 0) {
            Toast.makeText(requireContext(), "Producto no disponible", Toast.LENGTH_LONG).show()
        } else {
            Glide.with(requireContext()).load(args.productImageUrl).centerCrop()
                .into(binding.imgBackground)
            binding.txtProductTitle.text = args.title
            binding.txtDescription.text = args.description
            binding.txtRating.text = "Valoración ${args.rating}"
            binding.txtPrice.text = "Precio ${args.price}"
            binding.txtCategory.text = "Categoria ${args.category}"
            binding.txtStock.text = "Cantidad Disponible ${args.stock}"
            binding.txtBrand.text = "Marca ${args.stock}"
        }

    }
}