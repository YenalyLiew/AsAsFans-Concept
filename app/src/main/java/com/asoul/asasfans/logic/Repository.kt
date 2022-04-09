package com.asoul.asasfans.logic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.asoul.asasfans.logic.bean.VideoAdvancedSearchBean
import com.asoul.asasfans.logic.network.AsNetwork
import com.asoul.asasfans.logic.network.paging.AsoulVideoPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 09:21
 * @Description : Description...
 */
object Repository {
    val latestVersion = flow {
        val result = try {
            val latestVersionInfo = AsNetwork.upgradeService.getLatestVersion()
            Result.success(latestVersionInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun getAsoulVideo(
        order: String,
        advancedQuery: String? = null,
        copyright: Int? = null,
        tname: String? = null
    ): Flow<PagingData<VideoAdvancedSearchBean.Data.Result>> {
        return Pager(
            config = PagingConfig(pageSize = 2),
            pagingSourceFactory = { AsoulVideoPagingSource(order, advancedQuery, copyright, tname) }
        ).flow
    }
}