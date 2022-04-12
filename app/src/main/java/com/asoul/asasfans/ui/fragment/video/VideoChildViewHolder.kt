package com.asoul.asasfans.ui.fragment.video

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.asoul.asasfans.AsApplication
import com.asoul.asasfans.R
import com.asoul.asasfans.databinding.ItemVideoBinding
import com.asoul.asasfans.logic.bean.VideoAdvancedSearchBean
import com.asoul.asasfans.utils.showShortToast
import com.asoul.asasfans.utils.toDurationCase
import com.asoul.asasfans.utils.toViewsCase
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

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

    private val glideOptions = RequestOptions()
        .placeholder(R.drawable.loading)
        .error(R.drawable.load_failure)
        .diskCacheStrategy(DiskCacheStrategy.NONE)

    companion object {
        fun create(
            parent: ViewGroup,
            fragment: Fragment,
            item: (position: Int) -> VideoAdvancedSearchBean.Data.Result?
        ): VideoChildViewHolder {
            val binding =
                ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val holder = VideoChildViewHolder(binding)
            val packageManager = AsApplication.context.packageManager
            val checkBiliPackage =
                packageManager.getLaunchIntentForPackage(AsApplication.BILI_PACKAGE_NAME)
            // 如果需要setOnClickListener等，放这里。
            holder.itemView.setOnClickListener {
                val position = holder.bindingAdapterPosition
                val videoItem = item(position)
                if (videoItem != null) {
                    if (checkBiliPackage != null) {
                        val biliUri = Uri.parse("bilibili://video/${videoItem.bvid}")
                        val intent = Intent(Intent.ACTION_VIEW, biliUri)
                        fragment.startActivity(intent)
                    } else {
                        "你好像没有安装B站...建议长按复制BV号".showShortToast()
                    }
                } else {
                    "BV号获取失败！".showShortToast()
                }
            }
            holder.itemView.setOnLongClickListener {
                val position = holder.bindingAdapterPosition
                val videoItem = item(position)
                if (videoItem != null) {
                    val clipBoardManager =
                        AsApplication.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipDataSet = ClipData.newPlainText(null, videoItem.bvid)
                    clipBoardManager.setPrimaryClip(clipDataSet)
                    "BV号复制成功！".showShortToast()
                } else {
                    "BV号复制失败！".showShortToast()
                }
                true
            }
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
        Glide.with(fragment).load(videoItem?.pic)
            .apply(glideOptions)
            .into(binding.videoCover)
    }
}