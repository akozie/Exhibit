package com.example.seampay.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seampay.databinding.ExhibitItemLayoutBinding
import com.example.seampay.db.Exhibits

class ExhibitAdapter : ListAdapter<Exhibits, ExhibitAdapter.ItemViewHolder>(CarListComparator()) {

        private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder( viewGroup: ViewGroup, i: Int): ItemViewHolder {
        val binding = ExhibitItemLayoutBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder( itemViewHolder: ItemViewHolder, i: Int) {
        val currentItem = getItem(i)
        if (currentItem != null) {
            itemViewHolder.bind(currentItem)
        }


        // Create layout manager with initial prefetch item count
        val layoutManager = LinearLayoutManager(
            itemViewHolder.exhibitSubItemRecyclerView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager.initialPrefetchItemCount = currentItem.images.images.size

        // Create sub item view adapter
        val subItemAdapter = currentItem.images.let { ExhibitSubAdapter(it.images) }
        itemViewHolder.exhibitSubItemRecyclerView.layoutManager = layoutManager
        itemViewHolder.exhibitSubItemRecyclerView.adapter = subItemAdapter
        itemViewHolder.exhibitSubItemRecyclerView.setRecycledViewPool(viewPool)
    }


    inner class ItemViewHolder(private var binding: ExhibitItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val exhibitItemTitle: TextView = binding.iphoneText
        val exhibitSubItemRecyclerView: RecyclerView = binding.imageViewIphone

        fun bind(item: Exhibits) {
            binding.apply {
                exhibitItemTitle.text = item.title

            }
        }
    }





     class CarListComparator : DiffUtil.ItemCallback<Exhibits>() {
        override fun areItemsTheSame(oldItem: Exhibits, newItem: Exhibits) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Exhibits, newItem: Exhibits) =
            oldItem == newItem
    }
}
