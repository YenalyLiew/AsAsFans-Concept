package com.asoul.asasfans_concept.logic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.asoul.asasfans_concept.logic.bean.ImageDataBean
import com.asoul.asasfans_concept.logic.bean.VideoAdvancedSearchBean
import com.asoul.asasfans_concept.logic.network.AsNetwork
import com.asoul.asasfans_concept.logic.network.paging.AsoulVideoPagingSource
import com.asoul.asasfans_concept.logic.network.paging.FanArtPagingSource
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
        tname: String? = null,
        filter: ((VideoAdvancedSearchBean.Data.Result) -> Boolean)? = null
    ): Flow<PagingData<VideoAdvancedSearchBean.Data.Result>> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            pagingSourceFactory = {
                AsoulVideoPagingSource(order, advancedQuery, copyright, tname, filter)
            }
        ).flow
    }

    fun getAsoulFanArt(
        sort: Int? = null,
        part: Int? = null,
        rank: Int? = null,
        ctime: Long? = null,
        filter: ((ImageDataBean) -> Boolean)? = null
    ): Flow<PagingData<ImageDataBean>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                FanArtPagingSource(sort, part, rank, ctime, filter)
            }
        ).flow
    }
}