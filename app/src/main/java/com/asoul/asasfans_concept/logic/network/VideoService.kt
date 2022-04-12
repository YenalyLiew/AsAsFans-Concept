package com.asoul.asasfans_concept.logic.network

import com.asoul.asasfans_concept.logic.bean.VideoAdvancedSearchBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/09 009 15:40
 * @Description : Description...
 */
interface VideoService {

    @GET("v2/asoul-video-interface/advanced-search")
    suspend fun getAsoulVideo(
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("q") advancedQuery: String? = null,
        @Query("copyright") copyright: Int? = null,
        @Query("tname") tname: String? = null
    ): VideoAdvancedSearchBean
}