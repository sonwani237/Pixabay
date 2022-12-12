package com.exchange.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exchange.R
import com.exchange.databinding.PicsItemsBinding
import com.exchange.model.SearchPics
import com.exchange.viewModel.PicsItemViewModel

class PicsAdapter : RecyclerView.Adapter<PicsAdapter.MyViewHolder>() {

    var mDataList: MutableList<SearchPics.Pics>? = null

    init {
        mDataList = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val dataBinding: PicsItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.pics_items, parent, false)
        return MyViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return mDataList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindList(mDataList!![position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(dataList: MutableList<SearchPics.Pics>) {
        mDataList!!.addAll(dataList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemBinding: PicsItemsBinding) : RecyclerView.ViewHolder(itemBinding.item) {
        private var mItemBinding: PicsItemsBinding? = itemBinding

        fun bindList(pics: SearchPics.Pics) {
            if (mItemBinding!!.itemViewModel == null) {
                mItemBinding!!.itemViewModel = PicsItemViewModel(pics)
            } else {
                mItemBinding!!.itemViewModel!!.setData(pics)
            }
        }
    }

}