package com.asoul.asasfans.ui.fragment.video

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/07 007 09:24
 * @Description : Description...
 */
class VideoChildAdapter(fragment: Fragment) : FragmentStateAdapter(fragment.requireActivity()) {

    override fun getItemCount(): Int {
        return VideoChildFragmentEnum.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            VideoChildFragmentEnum.FAN_VIDEO_FRAGMENT.ordinal -> VideoChildFragment()
            VideoChildFragmentEnum.HOT_CUT_VIDEO_FRAGMENT.ordinal -> VideoChildFragment()
            VideoChildFragmentEnum.NEW_RELEASE_VIDEO_FRAGMENT.ordinal -> VideoChildFragment()
            VideoChildFragmentEnum.HISTORY_RECOMMEND_VIDEO_FRAGMENT.ordinal -> VideoChildFragment()
            else -> throw IndexOutOfBoundsException("out of bounds!")
        }
    }
}