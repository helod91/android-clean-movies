package com.omdb.cleanmovies.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<B : ViewBinding, I> : RecyclerView.Adapter<BaseAdapter.ViewHolder<B>>() {

    var data: List<I>? = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun presentBinding(parent: ViewGroup): B

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        return ViewHolder(presentBinding(parent))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class ViewHolder<B : ViewBinding>(var binding: B) : RecyclerView.ViewHolder(binding.root)

}