package com.svape.store.iu.adapters.viewHolder

import android.content.Context
import com.bumptech.glide.Glide
import com.svape.store.data.model.Product
import com.svape.store.databinding.ProductItemBinding
import com.svape.store.utils.BaseViewHolder

class ProductViewHolder(
    private val binding: ProductItemBinding,
    val context: Context
) :
    BaseViewHolder<Product>(binding.root) {
    override fun bind(item: Product) {
        Glide.with(context)
            .load(item.thumbnail)
            .centerCrop()
            .into(binding.ivProductImg)

        binding.tvProductTitle.text = item.title
        binding.tvBrand.text = item.brand
        binding.tvPrice.text = item.price.toString()
        binding.tvRating.text = item.rating.toString()
    }

}