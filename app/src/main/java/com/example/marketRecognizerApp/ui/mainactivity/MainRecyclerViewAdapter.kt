/*
package com.example.marketRecognizerApp.ui.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketRecognizerApp.databinding.ItemRecyclerviewBinding
import com.example.marketRecognizerApp.models.RoomModels

class MainRecyclerViewAdapter(
    private val context: Context,
    private var mutableListRoom: MutableList<RoomModels.DummyListModel?>
) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {


    internal var onItemSelected: (position: Int, item: RoomModels.DummyListModel?) -> Unit =
        { _, _ -> }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val binding = ItemRecyclerviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainRecyclerViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return mutableListRoom.size
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        mutableListRoom[position]?.let {
            holder.bindItems(
                holder,
                it,
                position,
                onItemSelected
            )
        }

    }

    class MainRecyclerViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(
            holder: MainRecyclerViewHolder,
            item: RoomModels.DummyListModel,
            position: Int,
            onItemSelected: (Int, RoomModels.DummyListModel) -> Unit
        ) {
            binding.textViewDummyTitle.text = item.dummy_2
            binding.constraintLayoutRecyclerView.setOnClickListener {
                onItemSelected(position, item)
            }
        }
    }

    fun updateDataSource(
        newDataSource: MutableList<RoomModels.DummyListModel?>
    ) {
        this.mutableListRoom = newDataSource
        notifyDataSetChanged()
    }

}*/
