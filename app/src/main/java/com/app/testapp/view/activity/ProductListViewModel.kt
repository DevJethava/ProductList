package com.app.testapp.view.activity

import com.app.testapp.helper.async
import com.app.testapp.model.data.ProductResponse
import com.app.testapp.model.repository.ApiRepository
import com.app.testapp.viewmodel.ParentViewModel
import io.reactivex.Single

class ProductListViewModel(
    repository: ApiRepository,
) : ParentViewModel(repository) {

    fun getProductByMarketId(mainCategoryId: String): Single<ProductResponse> {
        return repository.getProductByMarketId(mainCategoryId).async(0)
            .doOnSuccess { stopLoad() }
            .doOnSubscribe {
                startLoad()
            }
            .doAfterTerminate {
                stopLoad()
            }
    }

}