package com.ibnufth.gamedirectory.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibnufth.gamedirectory.core.R
import com.ibnufth.gamedirectory.core.databinding.GameListItemBinding
import com.ibnufth.gamedirectory.core.domain.model.Games

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private var listData = ArrayList<Games>()
    var onItemClick: ((Games) -> Unit)? = null

    fun setData(newListData: List<Games>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = GameListItemBinding.bind(itemView)
        fun bind(data: Games) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.thumbnail)
                    .into(imageView)
                tvTitle.text = data.title
                tvGenre.text = data.genre
                tvReleased.text = data.releaseDate
            }
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(listData[adapterPosition]) }
        }

    }
}