package com.asoul.asasfans.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.asoul.asasfans.logic.Repository
import com.asoul.asasfans.logic.bean.ImageDataBean
import com.asoul.asasfans.logic.bean.VideoAdvancedSearchBean

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 17:27
 * @Description : Description...
 */
class MainViewModel : ViewModel() {

    val latestVersion = Repository.latestVersion

    fun getAsoulVideo(
        order: String,
        advancedQuery: String? = null,
        copyright: Int? = null,
        tname: String? = null,
        filter: ((VideoAdvancedSearchBean.Data.Result) -> Boolean)? = null
    ) = Repository.getAsoulVideo(order, advancedQuery, copyright, tname, filter)
        .cachedIn(viewModelScope)

    var fanArtOrder: Int? = null
    var fanArtDate: Int? = null
    var fanArtPart: Int? = 0
    fun getAsoulFanArt(
        sort: Int? = null,
        part: Int? = null,
        rank: Int? = null,
        ctime: Long? = null,
        filter: ((ImageDataBean) -> Boolean)? = null
    ) = Repository.getAsoulFanArt(sort, part, rank, ctime, filter)
        .cachedIn(viewModelScope)
}