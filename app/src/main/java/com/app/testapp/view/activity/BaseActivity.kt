package com.app.testapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.app.testapp.viewmodel.ParentViewModel
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.SingleSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.Single

/**
 * BaseActivity
 * All activity parent class
 * contains Common methods for Activity
 */
open class BaseActivity : AppCompatActivity() {
    var superViewModel: ParentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

    }

    fun setParentViewModel(viewModel: ParentViewModel) {
        this.superViewModel = viewModel
    }

    fun <T> Single<T>.bindLifeCycle(owner: LifecycleOwner): SingleSubscribeProxy<T> =
        this.`as`(
            AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(
                    owner,
                    Lifecycle.Event.ON_DESTROY
                )
            )
        )
}