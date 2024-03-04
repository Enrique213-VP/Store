package com.svape.store.iu.Fragments.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.svape.store.R
import com.svape.store.data.local.ProductDatabase
import com.svape.store.data.local.localDataSource.LocalProductDataSource
import com.svape.store.data.model.Product
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.svape.store.data.remote.RemoteProductDataSource
import com.svape.store.databinding.FragmentProductBinding
import com.svape.store.domain.usecase.ProductRepositoryImp
import com.svape.store.domain.webService.RetrofitClient
import com.svape.store.iu.adapters.RecyclerViewProductAdapter
import com.svape.store.presentation.ProductViewModel
import com.svape.store.presentation.ProductViewModelFactory
import com.svape.store.utils.ResponseStatus
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.FragmentComponent

class ProductFragment : Fragment(R.layout.fragment_product),
    RecyclerViewProductAdapter.OnProductClickListener {

    private lateinit var binding: FragmentProductBinding
    private var recyclerViewProductAdapter: RecyclerViewProductAdapter? = null


    private val viewModel by viewModels<ProductViewModel> {
        ProductViewModelFactory(
            ProductRepositoryImp(
                RemoteProductDataSource(RetrofitClient.webservice),
                LocalProductDataSource(ProductDatabase.getDatabase(requireContext()).productDao())
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)

        viewModel.fetchMainScreenProducts().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResponseStatus.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.txtProductTitle.visibility = View.GONE
                }

                is ResponseStatus.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.txtProductTitle.visibility = View.VISIBLE

                    recyclerViewProductAdapter =
                        RecyclerViewProductAdapter(result.data.products, this@ProductFragment)
                    val layoutManager: RecyclerView.LayoutManager =
                        GridLayoutManager(requireContext(), 2)
                    binding.rvProducts.layoutManager = layoutManager
                    binding.rvProducts.adapter = recyclerViewProductAdapter
                }

                is ResponseStatus.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.txtProductTitle.visibility = View.VISIBLE
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })



        /*binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })*/
    }

    private fun filterList(query: String?) {
        recyclerViewProductAdapter?.filter(query)
    }



    override fun onProductClick(product: Product) {
        val action =
            ProductFragmentDirections.actionProductFragmentToProductDetailFragment(
                product.thumbnail,
                product.description,
                product.price,
                product.title,
                product.rating.toFloat(),
                product.stock,
                product.brand,
                product.category
            )
        findNavController().navigate(action)
    }
}