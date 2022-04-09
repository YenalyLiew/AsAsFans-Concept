package com.asoul.asasfans.ui.fragment.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asoul.asasfans.databinding.FragmentVideoChildBinding

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/07 007 08:56
 * @Description : Description...
 */
class VideoChildFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(which: Int): VideoChildFragment {
            val fragment = VideoChildFragment()
            val bundle = Bundle()
            bundle.putInt("which_video_fragment", which)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentVideoChildBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoChildBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val whichVideoFragment = arguments?.getInt("which_video_fragment")
        when (whichVideoFragment) {
            VideoChildFragmentEnum.FAN_VIDEO_FRAGMENT.ordinal -> {

            }
            VideoChildFragmentEnum.HOT_CUT_VIDEO_FRAGMENT.ordinal -> {

            }
            VideoChildFragmentEnum.NEW_RELEASE_VIDEO_FRAGMENT.ordinal -> {

            }
            VideoChildFragmentEnum.HISTORY_RECOMMEND_VIDEO_FRAGMENT.ordinal -> {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}