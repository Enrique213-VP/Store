package com.svape.store.iu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.svape.store.data.model.Product
import com.svape.store.databinding.ProductItemBinding
import com.svape.store.iu.adapters.viewHolder.ProductViewHolder
import com.svape.store.utils.BaseViewHolder

class RecyclerViewProductAdapter(
    private val productList: List<Product>,
    private val itemClickListener: OnProductClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var filteredList: List<Product> = productList

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ProductViewHolder(itemBinding, parent.context)

        //Element to be defined if 0 not see
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener
            itemClickListener.onProductClick(productList[position])
        }
        return holder
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ProductViewHolder -> holder.bind(productList[position])
        }
    }

    fun filter(query: String?) {
        filteredList = if (query.isNullOrBlank()) {
            productList // Si la consulta está vacía, muestra la lista completa
        } else {
            productList.filter { product ->
                // Filtra según tus criterios, por ejemplo, el nombre del producto
                product.title.contains(query, ignoreCase = true)
            }
        }

        notifyDataSetChanged()
    }

}