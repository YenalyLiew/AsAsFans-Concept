package com.asoul.asasfans.ui.fragment.video

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asoul.asasfans.logic.bean.VideoAdvancedSearchBean

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/07 007 20:27
 * @Description : Description...
 */
class VideoChildRvAdapter(private val fragment: VideoChildFragment) :
    PagingDataAdapter<VideoAdvancedSearchBean.Data.Result, RecyclerView.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR =
            object : DiffUtil.ItemCallback<VideoAdvancedSearchBean.Data.Result>() {
                override fun areItemsTheSame(
                    oldItem: VideoAdvancedSearchBean.Data.Result,
                    newItem: VideoAdvancedSearchBean.Data.Result
                ): Boolean {
                    return oldItem.bvid == newItem.bvid
                }

                override fun areContentsTheSame(
                    oldItem: VideoAdvancedSearchBean.Data.Result,
                    newItem: VideoAdvancedSearchBean.Data.Result
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val videoItem = getItem(position)
        (holder as VideoChildViewHolder).bind(videoItem, fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VideoChildViewHolder.create(parent, fragment)
    }
}