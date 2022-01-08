package com.app.testapp.model.repository


import com.app.testapp.model.data.ProductResponse
import com.app.testapp.model.remote.RestApiService
import io.reactivex.Single

interface ApiRepository {

    fun getProductByMarketId(mainCategoryId: String): Single<ProductResponse>
}

class ApiRepositoryImpl constructor(
    private val remote: RestApiService,
) : ApiRepository {

    override fun getProductByMarketId(mainCategoryId: String) =
        remote.getProductByMarketId(mainCategoryId = mainCategoryId)
}

