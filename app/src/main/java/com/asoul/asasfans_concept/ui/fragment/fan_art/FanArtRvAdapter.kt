package com.asoul.asasfans_concept.ui.fragment.fan_art

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asoul.asasfans_concept.logic.bean.ImageDataBean
import com.asoul.asasfans_concept.ui.fragment.FanArtFragment

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/10 010 19:57
 * @Description : Description...
 */
class FanArtRvAdapter(private val fragment: FanArtFragment) :
    PagingDataAdapter<ImageDataBean, RecyclerView.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ImageDataBean>() {
            override fun areItemsTheSame(oldItem: ImageDataBean, newItem: ImageDataBean): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(
                oldItem: ImageDataBean,
                newItem: ImageDataBean
            ): Boolean {
                // 原来那个Bean没有重写toString，暂时用uid和dy_id相等代替contents相等
                return oldItem.uid == newItem.uid && oldItem.dy_id == newItem.dy_id
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fanArtItem = getItem(position)
        (holder as FanArtViewHolder).bind(fanArtItem, fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FanArtViewHolder.create(parent, fragment) { position -> getItem(position) }
    }
}