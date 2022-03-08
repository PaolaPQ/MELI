package com.jppq.catalog.app.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jppq.catalog.R
import com.jppq.catalog.app.extensions.loadImage
import com.jppq.catalog.app.extensions.priceFormat
import com.jppq.catalog.app.view.model.Product
import com.jppq.catalog.databinding.ItemProductBinding

class ProductAdapter(
    private val context: Context,
    private val items: List<Product> = listOf(),
    private val onClick: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.icon
        val name = binding.title
        val price = binding.subtitle
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = items[position]

        holder.apply {
            name.text = product.title
            price.text = product.price.priceFormat()

            image.loadImage(
                product.thumbnail,
                context,
                R.drawable.ic_loading_image,
                R.drawable.ic_no_image
            )

            holder.itemView.setOnClickListener {
                onClick(product)
            }
        }
    }
}