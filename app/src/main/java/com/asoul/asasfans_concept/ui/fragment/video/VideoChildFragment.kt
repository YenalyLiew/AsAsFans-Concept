package com.asoul.asasfans_concept.ui.fragment.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asoul.asasfans_concept.databinding.FragmentVideoChildBinding
import com.asoul.asasfans_concept.logic.bean.VideoAdvancedSearchBean
import com.asoul.asasfans_concept.ui.viewmodel.MainViewModel
import com.asoul.asasfans_concept.utils.showShortToast
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/07 007 08:56
 * @Description : Description...
 */
class VideoChildFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(which: VideoChildFragmentEnum): VideoChildFragment {
            val fragment = VideoChildFragment()
            val bundle = Bundle()
            bundle.putSerializable("which_video_fragment", which)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentVideoChildBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()

    private val videoAdapter by lazy { VideoChildRvAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoChildBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.videoChildRv.layoutManager = LinearLayoutManager(activity)
        binding.videoChildRv.adapter = videoAdapter
        binding.videoChildRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            // 当滑动时不加载图片，当滑动停止时再加载图片。
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(this@VideoChildFragment).resumeRequests()
                } else {
                    Glide.with(this@VideoChildFragment).pauseRequests()
                }
            }
        })
        videoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error -> {
                    "加载错误，网络可能出现问题".showShortToast()
                }
                else -> {}
            }
        }
        // 获取当前时间戳，秒级
        val timestamp = System.currentTimeMillis() / 1000
        // 获取三天以内的所有作品，秒级
        val fifteenDaysTimestamp = 25_9200L
        when (arguments?.getSerializable("which_video_fragment") as VideoChildFragmentEnum) {
            VideoChildFragmentEnum.FAN_VIDEO_FRAGMENT -> {
                getAsoulVideo(
                    order = "score",
                    copyright = 1,
                    advancedQuery = "pubdate.${timestamp - fifteenDaysTimestamp}+$timestamp.BETWEEN"
                )
            }
            VideoChildFragmentEnum.HOT_CUT_VIDEO_FRAGMENT -> {
                getAsoulVideo(
                    order = "score",
                    copyright = 2,
                    advancedQuery = "pubdate.${timestamp - fifteenDaysTimestamp}+$timestamp.BETWEEN"
                )
            }
            VideoChildFragmentEnum.NEW_RELEASE_VIDEO_FRAGMENT -> {
                getAsoulVideo(
                    order = "pubdate"
                )
            }
            VideoChildFragmentEnum.HISTORY_RECOMMEND_VIDEO_FRAGMENT -> {
                getAsoulVideo(
                    order = "score"
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getAsoulVideo(
        order: String,
        advancedQuery: String? = null,
        copyright: Int? = null,
        tname: String? = null,
        filter: ((VideoAdvancedSearchBean.Data.Result) -> Boolean)? = null
    ) {
        lifecycleScope.launch {
            viewModel
                .getAsoulVideo(order, advancedQuery, copyright, tname, filter)
                .collect(videoAdapter::submitData)
        }
    }
}