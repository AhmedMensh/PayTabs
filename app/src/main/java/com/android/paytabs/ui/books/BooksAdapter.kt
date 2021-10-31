package com.android.paytabs.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.android.paytabs.R
import com.android.paytabs.databinding.ListItemBookBinding
import com.android.paytabs.models.BooksResponseModel

class BooksAdapter() :
    ListAdapter<BooksResponseModel, BooksAdapter.ViewHolder>(DiffCallback) {


    class ViewHolder(val itemBinding: ListItemBookBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

    object DiffCallback : DiffUtil.ItemCallback<BooksResponseModel>() {
        override fun areItemsTheSame(
            oldItem: BooksResponseModel,
            newItem: BooksResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BooksResponseModel,
            newItem: BooksResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingItem = DataBindingUtil.inflate<ListItemBookBinding>(
            layoutInflater,
            R.layout.list_item_book,
            parent,
            false
        )
        return ViewHolder(bindingItem)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemBinding.model = getItem(position)
    }
}