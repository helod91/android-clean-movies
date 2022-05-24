package com.omdb.cleanmovies.common

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.omdb.domain.models.Data
import com.omdb.domain.models.Status

abstract class BaseActivity<V : ViewBinding, M : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: V

    protected abstract val viewModel: M

    protected open var showBackButton = false

    abstract fun presentBinding(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = presentBinding()

        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)

        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun <RequestData> observeSuccess(
        liveData: LiveData<Data<RequestData>>,
        successAction: (result: RequestData?) -> Unit,
        progress: ProgressBar? = null
    ) {
        liveData.observe(this, Observer {
            progress?.visibility = View.GONE

            when (it.responseType) {
                Status.LOADING -> progress?.visibility = View.VISIBLE
                Status.SUCCESSFUL -> successAction.invoke(it.data)
                Status.ERROR ->
                    AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage(it.error?.message)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()

            }
        })
    }
}