package com.asoul.asasfans.logic.network.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asoul.asasfans.logic.bean.ImageDataBean
import com.asoul.asasfans.logic.network.AsNetwork

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/10 010 10:09
 * @Description : Description...
 */
class FanArtPagingSource(
    private val sort: Int? = null,
    private val part: Int? = null,
    private val rank: Int? = null,
    private val ctime: Long? = null,
    private val filter: ((ImageDataBean) -> Boolean)? = null
) : PagingSource<Int, ImageDataBean>() {

    override fun getRefreshKey(state: PagingState<Int, ImageDataBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDataBean> {
        return try {
            val page = params.key ?: 1
            val response = AsNetwork.fanArtService.getFanArt(page, sort, part, rank, ctime)

            val result = if (filter != null) {
                response.filter(filter)
            } else {
                response
            }

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (result.isNotEmpty()) page + 1 else null
            LoadResult.Page(result, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}