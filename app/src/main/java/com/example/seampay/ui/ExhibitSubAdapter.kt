package com.example.seampay.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.seampay.databinding.ExhibitSubItemLayoutBinding
import com.squareup.picasso.Picasso

class ExhibitSubAdapter(private val itemList: List<String>):
    RecyclerView.Adapter<ExhibitSubAdapter.ItemViewHolder?>() {
    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    inner class ItemViewHolder(private val binding: ExhibitSubItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val exhibitImageView: ImageView = binding.imgIphone

    }

    override fun onCreateViewHolder( viewGroup: ViewGroup, i: Int): ItemViewHolder {
        val view =
            ExhibitSubItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup,
                false)
        return ItemViewHolder(view)
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder( itemViewHolder: ItemViewHolder, i: Int) {
        val item: String = itemList[i]
        Picasso.get().load(item).into(itemViewHolder.exhibitImageView)

    }

}