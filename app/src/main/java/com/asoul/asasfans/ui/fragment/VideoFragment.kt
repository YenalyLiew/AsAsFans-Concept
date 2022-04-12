package com.asoul.asasfans.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.asoul.asasfans.R
import com.asoul.asasfans.databinding.FragmentVideoBinding
import com.asoul.asasfans.ui.fragment.video.VideoChildAdapter
import com.asoul.asasfans.ui.fragment.video.VideoChildFragmentEnum
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 20:11
 * @Description : Description...
 */
class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("VideoFragment", "Launched")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.videoToolbar)
        }
        binding.videoViewPager.adapter = VideoChildAdapter(this)

        TabLayoutMediator(binding.videoTabLayout, binding.videoViewPager) { tab, position ->
            when (position) {
                VideoChildFragmentEnum.FAN_VIDEO_FRAGMENT.ordinal -> tab.setText(R.string.fan_video)
                VideoChildFragmentEnum.HOT_CUT_VIDEO_FRAGMENT.ordinal -> tab.setText(R.string.hot_cut_video)
                VideoChildFragmentEnum.NEW_RELEASE_VIDEO_FRAGMENT.ordinal -> tab.setText(R.string.new_release_video)
                VideoChildFragmentEnum.HISTORY_RECOMMEND_VIDEO_FRAGMENT.ordinal -> tab.setText(R.string.history_recommend_video)
            }
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {

            }
        }
        return true
    }
}