package com.app.testapp.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.testapp.R
import com.app.testapp.databinding.ActivityMainBinding
import com.app.testapp.helper.Utils
import com.app.testapp.helper.toast
import com.app.testapp.model.data.Product
import com.app.testapp.model.data.ProductResponse
import com.app.testapp.view.adapter.ProductListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), ProductListAdapter.OnClick {

    private val TAG = MainActivity::class.simpleName

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<ProductListViewModel>()

    lateinit var adapter: ProductListAdapter

    private var productResponse: ProductResponse? = null
    var productList: List<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.activity = this
        binding.vm = viewModel
        setContentView(binding.root)

        init()
    }

    private fun init() {
        loadProduct()
    }

    private fun loadProduct() {
        val dialog: AlertDialog = Utils.setupLoadingDialog(this)
        if (Utils.isConnectionAvailable(this)) {
            dialog.show()
            viewModel.getProductByMarketId("0c6a36ba-10e4-438f-ba86-0d5b68a2bb29")
                .bindLifeCycle(this).subscribe(
                    {
                        dialog.dismiss()
                        productResponse = it
                        if (productResponse != null) {
                            productList = productResponse!!.response
                            binding.rvProductList.layoutManager = LinearLayoutManager(this)
                            adapter = ProductListAdapter(
                                context = this,
                                productList = productList,
                                onClick = this
                            )
                            binding.rvProductList.adapter = adapter
                        }
                    },
                    {
                        dialog.dismiss()
                        Log.e(TAG!!, it.message!!)

                    }
                )
            dialog.dismiss()
        } else {
            dialog.dismiss()
            this.toast(getString(R.string.internet_not_available))
        }
    }

    override fun onProductClick(product: Product) {
        Log.e(TAG, product.toString())
        val url = product.videoUrl ?: product.productPic1
        toast(url!!)
    }
}