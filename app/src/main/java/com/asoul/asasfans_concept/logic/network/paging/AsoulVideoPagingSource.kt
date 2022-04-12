package com.asoul.asasfans_concept.logic.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asoul.asasfans_concept.logic.bean.VideoAdvancedSearchBean
import com.asoul.asasfans_concept.logic.network.AsNetwork

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/07 007 21:17
 * @Description : Description...
 */
class AsoulVideoPagingSource(
    private val order: String,
    private val advancedQuery: String? = null,
    private val copyright: Int? = null,
    private val tname: String? = null,
    private val filter: ((VideoAdvancedSearchBean.Data.Result) -> Boolean)? = null
) : PagingSource<Int, VideoAdvancedSearchBean.Data.Result>() {

    override fun getRefreshKey(state: PagingState<Int, VideoAdvancedSearchBean.Data.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoAdvancedSearchBean.Data.Result> {
        return try {
            val page = params.key ?: 1
            val response =
                AsNetwork.videoService.getAsoulVideo(order, page, advancedQuery, copyright, tname)

            val result = if (filter != null) {
                response.data.result.filter(filter)
            } else {
                response.data.result
            }

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (result.isNotEmpty()) page + 1 else null
            LoadResult.Page(result, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}