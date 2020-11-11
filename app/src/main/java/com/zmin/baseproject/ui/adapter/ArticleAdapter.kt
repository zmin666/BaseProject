package com.zmin.baseproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zmin.baseproject.R
import com.zmin.baseproject.data.model.bean.AriticleResponse
import com.zmin.mvvm.model.bean.netbean.ListDataUiState
import kotlinx.android.synthetic.main.item.view.*

/**
 * @author: ZhangMin
 * @date: 2020/11/11 10:05
 * @version: 1.0
 * @desc:
 */
class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.InnerViewHolder>() {

    private val mContentList = arrayListOf<AriticleResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return InnerViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.itemView.apply {
            tv_name.text = mContentList[position].title
        }
    }

    fun setData(it: ListDataUiState<AriticleResponse>) {
        mContentList.clear()
        mContentList.addAll(it.listData)
        notifyDataSetChanged()
    }


    inner class InnerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_name: TextView = itemView.findViewById(R.id.tv_name)
    }

}