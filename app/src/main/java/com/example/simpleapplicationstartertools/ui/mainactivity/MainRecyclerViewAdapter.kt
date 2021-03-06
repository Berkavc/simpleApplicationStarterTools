package com.example.simpleapplicationstartertools.ui.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleapplicationstartertools.databinding.ItemRecyclerviewBinding
import com.example.simpleapplicationstartertools.models.DummyModels

class MainRecyclerViewAdapter(
    private val context: Context,
    private var mutableListDummy: MutableList<DummyModels.DummyListModel?>
) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {


    internal var onItemSelected: (position: Int, item: DummyModels.DummyListModel?) -> Unit =
        { _, _ -> }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val binding = ItemRecyclerviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainRecyclerViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return mutableListDummy.size
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        mutableListDummy[position]?.let {
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
            item: DummyModels.DummyListModel,
            position: Int,
            onItemSelected: (Int, DummyModels.DummyListModel) -> Unit
        ) {
            binding.textViewDummyTitle.text = item.dummy_2
            binding.constraintLayoutRecyclerView.setOnClickListener {
                onItemSelected(position, item)
            }
        }
    }

    fun updateDataSource(
        newDataSource: MutableList<DummyModels.DummyListModel?>
    ) {
        this.mutableListDummy = newDataSource
        notifyDataSetChanged()
    }

}