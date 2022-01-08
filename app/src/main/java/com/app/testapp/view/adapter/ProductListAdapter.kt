package com.app.testapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.databinding.RawProductListBinding
import com.app.testapp.helper.Utils
import com.app.testapp.model.data.Product

class ProductListAdapter(
    private val context: Context,
    private val productList: List<Product>?,
    private val onClick: OnClick
) : RecyclerView.Adapter<ProductListAdapter.Holder>() {

    inner class Holder(val binding: RawProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cardRoot.setOnClickListener {
                onClick.onProductClick(productList!![adapterPosition])
            }
        }
    }

    interface OnClick {
        fun onProductClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = RawProductListBinding.inflate(inflater)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val params = RelativeLayout.LayoutParams(Utils.getScreenWidth(), Utils.getScreenWidth() / 2)
        holder.binding.ivProductImage.layoutParams = params

        holder.binding.tvTitle.text = productList?.get(position)?.title ?: ""
        holder.binding.tvMobileNo.text = productList?.get(position)?.productUploaderMobileNo ?: ""

        val fileName: String = productList?.get(position)?.fileName ?: ""
        if (fileName == "image") {
            Utils.loadImage(
                context,
                holder.binding.ivProductImage,
                productList?.get(position)?.productPic1!!
            )
        } else {
            Utils.loadThumbnail(
                context,
                holder.binding.ivProductImage,
                productList?.get(position)?.videoUrl!!
            )
        }
    }

    override fun getItemCount(): Int {
        if (productList == null)
            return 0
        return productList.size
    }
}