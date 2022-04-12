package com.asoul.asasfans_concept.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.asoul.asasfans_concept.R
import com.asoul.asasfans_concept.databinding.FragmentFanArtBinding
import com.asoul.asasfans_concept.logic.bean.ImageDataBean
import com.asoul.asasfans_concept.ui.fragment.fan_art.FanArtRvAdapter
import com.asoul.asasfans_concept.ui.viewmodel.MainViewModel
import com.asoul.asasfans_concept.utils.showShortToast
import kotlinx.coroutines.launch

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/07 007 08:03
 * @Description : Description...
 */
class FanArtFragment : Fragment() {

    private var _binding: FragmentFanArtBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()

    private val fanArtAdapter by lazy { FanArtRvAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFanArtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.fanArtRv.layoutManager = layoutManager
        binding.fanArtRv.adapter = fanArtAdapter

        fanArtAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error -> {
                    "加载错误，网络可能出现问题".showShortToast()
                }
                is LoadState.NotLoading -> {
                }
                is LoadState.Loading -> {
                }
            }
        }

        initSpinner()
        initChip()

        getAsoulFanArt(
            sort = viewModel.fanArtOrder,
            part = viewModel.fanArtPart,
            rank = viewModel.fanArtDate
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getAsoulFanArt(
        sort: Int? = null,
        part: Int? = null,
        rank: Int? = null,
        ctime: Long? = null,
        filter: ((ImageDataBean) -> Boolean)? = null
    ) {
        lifecycleScope.launch {
            viewModel.getAsoulFanArt(sort, part, rank, ctime, filter)
                .collect {
                    fanArtAdapter.submitData(it)
                    binding.fanArtRv.smoothScrollToPosition(0)
                }
        }
    }

    private fun initSpinner() {
        binding.spinnerOrder.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    Sort.PUB_DATE_IMAGE.ordinal -> {
                        viewModel.fanArtOrder = Sort.PUB_DATE_IMAGE.value
                        Log.d("FAN_ART", "已选择最新发布")
                    }
                    Sort.BILI_HOT_IMAGE.ordinal -> {
                        viewModel.fanArtOrder = Sort.BILI_HOT_IMAGE.value
                        Log.d("FAN_ART", "已选择B站热门")
                    }
                }
                getAsoulFanArt(
                    sort = viewModel.fanArtOrder,
                    part = viewModel.fanArtPart,
                    rank = viewModel.fanArtDate
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.spinnerDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        viewModel.fanArtDate = 0
                        Log.d("FAN_ART", "已选择无榜单")
                    }
                    1 -> {
                        viewModel.fanArtDate = 1
                        Log.d("FAN_ART", "已选择日榜")
                    }
                    2 -> {
                        viewModel.fanArtDate = 2
                        Log.d("FAN_ART", "已选择周榜")
                    }
                    3 -> {
                        viewModel.fanArtDate = 3
                        Log.d("FAN_ART", "已选择月榜")
                    }
                }
                getAsoulFanArt(
                    sort = viewModel.fanArtOrder,
                    part = viewModel.fanArtPart,
                    rank = viewModel.fanArtDate
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun initChip() {
        binding.fanArtChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chip_asoul -> {
                    viewModel.fanArtPart = Part.ASOUL.value
                }
                R.id.chip_ava -> {
                    viewModel.fanArtPart = Part.AVA.value
                }
                R.id.chip_bella -> {
                    viewModel.fanArtPart = Part.BELLA.value
                }
                R.id.chip_carol -> {
                    viewModel.fanArtPart = Part.CAROL.value
                }
                R.id.chip_diana -> {
                    viewModel.fanArtPart = Part.DIANA.value
                }
                R.id.chip_eileen -> {
                    viewModel.fanArtPart = Part.EILEEN.value
                }
                R.id.chip_bella_carol -> {
                    viewModel.fanArtPart = Part.BELLA_CAROL.value
                }
                R.id.chip_eileen_bella -> {
                    viewModel.fanArtPart = Part.EILEEN_BELLA.value
                }
                R.id.chip_diana_ava -> {
                    viewModel.fanArtPart = Part.DIANA_AVA.value
                }
                R.id.chip_eileen_carol -> {
                    viewModel.fanArtPart = Part.EILEEN_CAROL.value
                }
                R.id.chip_carol_eileen -> {
                    viewModel.fanArtPart = Part.CAROL_EILEEN.value
                }
                R.id.chip_eileen_ava -> {
                    viewModel.fanArtPart = Part.EILEEN_AVA.value
                }
                R.id.chip_eileen_diana -> {
                    viewModel.fanArtPart = Part.EILEEN_DIANA.value
                }
                else -> {
                    viewModel.fanArtPart = Part.ALL_TAG.value
                }
            }
            getAsoulFanArt(
                sort = viewModel.fanArtOrder,
                part = viewModel.fanArtPart,
                rank = viewModel.fanArtDate
            )
        }
    }

    private enum class Sort(val value: Int) {
        PUB_DATE_IMAGE(3),
        BILI_HOT_IMAGE(4)
    }

    private enum class Part(val value: Int) {
        ALL_TAG(0),
        ASOUL(1712619),
        AVA(9221368),
        BELLA(195579),
        CAROL(17872743),
        DIANA(17520266),
        EILEEN(17839311),
        BELLA_CAROL(18207897),
        EILEEN_BELLA(18843054),
        DIANA_AVA(17895874),
        EILEEN_CAROL(21134102),
        CAROL_EILEEN(18579605),
        EILEEN_AVA(1058727),
        EILEEN_DIANA(20064249)
    }
}