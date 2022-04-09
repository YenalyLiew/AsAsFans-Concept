package com.asoul.asasfans.ui.fragment

import androidx.fragment.app.Fragment

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/07 007 08:03
 * @Description : Description...
 */
class FanArtFragment : Fragment() {

    private enum class Sort(value: Int) {
        PUB_DATE_IMAGE(3),
        BILI_HOT_IMAGE(4)
    }

    private enum class Part(value: Int) {
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