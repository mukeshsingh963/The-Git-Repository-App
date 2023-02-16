package com.learning.thegitrepoapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.learning.thegitrepoapp.R
import com.learning.thegitrepoapp.databinding.ListItemBinding
import com.learning.thegitrepoapp.jetpack.model.ItemModel

class ReposAdapter(
    private val context: Context,
    private val listener: (Int, ItemModel, Int) -> Unit
) : RecyclerView.Adapter<ReposAdapter.ItemVH>() {

    private val itemList = ArrayList<ItemModel>()

    inner class ItemVH(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {
            binding.shareBtn.setOnClickListener(this)
            binding.deleteBtn.setOnClickListener(this)
            binding.browserBtn.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            commonClick(v, (itemList[adapterPosition]), adapterPosition)
        }

        private fun commonClick(v: View?, itemModel: ItemModel, adapterPosition:Int){
            when(v!!.id){
                R.id.share_btn -> listener(adapterPosition, itemModel, ACTION_SHARE)
                        R.id.delete_btn ->listener(adapterPosition, itemModel, ACTION_DELETE)
                                R.id.browser_btn -> listener(adapterPosition, itemModel, ACTION_OPEN)
            }
        }

        fun bindTo(itemModel: ItemModel, position: Int) {
            binding.tvTitle.text = itemModel.userName
            binding.tvDesc.text = itemModel.description
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(
            ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }
    private fun commonClick(v: View?, itemModel: ItemModel, position: Int) {

    }


    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bindTo(itemList[position], position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun openBrowser(url: String, context: Context) {
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(context,urlIntent,Bundle())

    }

    fun updateList(newList: List<ItemModel>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    companion object {
        const val ACTION_DELETE = 2
        const val ACTION_SHARE = 1
        const val ACTION_OPEN = 0

    }

}