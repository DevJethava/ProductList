package com.app.testapp.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("status")
    @Expose
    val status: Int,
    @SerializedName("response")
    @Expose
    val response: List<Product>? = null
)

data class Product(
    @SerializedName("Title")
    @Expose
    val title: String? = null,
    @SerializedName("ProductUploader_MobileNo")
    @Expose
    val productUploaderMobileNo: String? = null,
    @SerializedName("FileName")
    @Expose
    val fileName: String? = null,
    @SerializedName("VideoUrl")
    @Expose
    val videoUrl: String? = null,
    @SerializedName("ProductPic1")
    @Expose
    val productPic1: String? = null,
)