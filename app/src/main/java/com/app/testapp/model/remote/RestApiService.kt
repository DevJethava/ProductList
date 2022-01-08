package com.app.testapp.model.remote

import com.app.testapp.model.data.ProductResponse
import io.reactivex.Single
import retrofit2.http.*

interface RestApiService {
    // ProductByMarketId
    @FormUrlEncoded
    @POST("product/ProductByMarketId")
    fun getProductByMarketId(
        @Field("MainCategoryId") mainCategoryId: String,
    ): Single<ProductResponse>

}