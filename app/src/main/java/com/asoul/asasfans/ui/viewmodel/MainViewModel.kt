package com.asoul.asasfans.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.asoul.asasfans.logic.Repository

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
        tname: String? = null
    ) = Repository.getAsoulVideo(order, advancedQuery, copyright, tname)
        .cachedIn(viewModelScope)
}