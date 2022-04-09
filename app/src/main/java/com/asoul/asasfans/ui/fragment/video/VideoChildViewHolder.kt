package com.asoul.asasfans.ui.fragment.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.asoul.asasfans.databinding.ItemVideoBinding
import com.asoul.asasfans.logic.bean.VideoAdvancedSearchBean
import com.asoul.asasfans.utils.toDurationCase
import com.asoul.asasfans.utils.toViewsCase
import com.bumptech.glide.Glide

/**
 * 把ViewHolder单独拎出来是有原因的，是为了后期让Paging3更好扩展。
 *
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/09 009 16:35
 * @Description : Description...
 */
class VideoChildViewHolder(private val binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, fragment: Fragment): VideoChildViewHolder {
            val binding =
                ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val holder = VideoChildViewHolder(binding)
            // 如果需要setOnClickListener等，放这里。
            return holder
        }
    }

    fun bind(videoItem: VideoAdvancedSearchBean.Data.Result?, fragment: Fragment) {
        binding.videoPartition.text = videoItem?.tname
        binding.videoComments.text = videoItem?.danmaku?.toViewsCase()
        binding.videoTitle.text = videoItem?.title
        binding.videoUp.text = videoItem?.name
        binding.videoViews.text = videoItem?.view?.toViewsCase()
        binding.videoDuration.text = videoItem?.duration?.toInt()?.toDurationCase()
        Glide.with(fragment).load(videoItem?.pic).into(binding.videoCover)
    }
}