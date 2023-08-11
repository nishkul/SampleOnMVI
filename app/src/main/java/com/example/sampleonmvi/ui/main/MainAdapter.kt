package com.example.sampleonmvi.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleonmvi.data.model.FakeUserDataDTO
import com.example.sampleonmvi.databinding.ItemMainAdaptorBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {


    var list = mutableListOf<FakeUserDataDTO>()


    fun addItems(list: List<FakeUserDataDTO?>) {
        this.list.addAll(list as List<FakeUserDataDTO>)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val viewDataBinding: ItemMainAdaptorBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        val binding =
            ItemMainAdaptorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MyViewHolder, position: Int) {
        val binding = holder.viewDataBinding
        val item = this.list[position]

        binding.tvTitle.text = item.title
        binding.tvDesc.text = item.body
        binding.tvUserId.text = item.userId.toString()

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}