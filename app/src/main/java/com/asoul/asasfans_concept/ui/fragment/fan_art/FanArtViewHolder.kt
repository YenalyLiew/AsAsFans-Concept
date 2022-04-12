package com.asoul.asasfans_concept.ui.fragment.fan_art

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.asoul.asasfans_concept.AsApplication
import com.asoul.asasfans_concept.R
import com.asoul.asasfans_concept.databinding.ItemFanArtBinding
import com.asoul.asasfans_concept.logic.bean.ImageDataBean
import com.asoul.asasfans_concept.utils.dp
import com.asoul.asasfans_concept.utils.screenWidth
import com.asoul.asasfans_concept.utils.showShortToast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/10 010 19:47
 * @Description : Description...
 */
class FanArtViewHolder(private val binding: ItemFanArtBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val glideOptions = RequestOptions()
        .placeholder(R.drawable.loading)
        .error(R.drawable.load_failure)
        .diskCacheStrategy(DiskCacheStrategy.NONE)

    companion object {
        fun create(
            parent: ViewGroup,
            fragment: Fragment,
            item: (position: Int) -> ImageDataBean?
        ): FanArtViewHolder {
            val binding =
                ItemFanArtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val holder = FanArtViewHolder(binding)
            val packageManager = AsApplication.context.packageManager
            val checkBiliPackage =
                packageManager.getLaunchIntentForPackage(AsApplication.BILI_PACKAGE_NAME)
            // 如果需要setOnClickListener等，放这里。
            holder.itemView.setOnClickListener {
                val position = holder.bindingAdapterPosition
                val fanArtItem = item(position)
                if (fanArtItem != null) {
                    if (checkBiliPackage != null) {
                        val biliUri = Uri.parse("bilibili://following/detail/${fanArtItem.dy_id}")
                        val intent = Intent(Intent.ACTION_VIEW, biliUri)
                        fragment.startActivity(intent)
                    } else {
                        "你好像没有安装B站...".showShortToast()
                    }
                } else {
                    "插画ID获取失败！".showShortToast()
                }
            }
            return holder
        }
    }

    fun bind(fanArtItem: ImageDataBean?, fragment: Fragment) {
        binding.fanArtUp.text = fanArtItem?.name
        binding.fanArtNum.text = fanArtItem?.pic_url?.size?.toString()
        val firstFanArtUrl = fanArtItem?.pic_url?.get(0)
        val layoutParams = binding.fanArt.layoutParams as LinearLayout.LayoutParams
        val imageWidth: Double
        val imageHeight: Double
        if (firstFanArtUrl != null) {
            if (firstFanArtUrl.img_width >= 480) {
                imageWidth = 480.0
                imageHeight = imageWidth * firstFanArtUrl.img_height / firstFanArtUrl.img_width
            } else {
                imageWidth = firstFanArtUrl.img_width
                imageHeight = firstFanArtUrl.img_height
            }

            // 瀑布流自适应高度设置
            val itemWidth = screenWidth / 2 - 8.dp * 4
            layoutParams.width = itemWidth
            val scale = itemWidth / imageWidth
            layoutParams.height = (imageHeight * scale).toInt()
            binding.fanArt.layoutParams = layoutParams

            val firstFanArtThumbnail =
                "${firstFanArtUrl.img_src}@${imageWidth.toInt()}w_${imageHeight.toInt()}h_1e_1c.jpg"
            Glide.with(fragment).load(firstFanArtThumbnail)
                .apply(glideOptions)
                .override(layoutParams.width, layoutParams.height)
                .into(binding.fanArt)
        }
    }
}