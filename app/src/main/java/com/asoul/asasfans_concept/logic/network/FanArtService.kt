package com.asoul.asasfans_concept.logic.network

import com.asoul.asasfans_concept.logic.bean.ImageDataBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 16:46
 * @Description : Description...
 */
interface FanArtService {
    @GET("getPic")
    suspend fun getFanArt(
        @Query("page") page: Int,
        @Query("sort") sort: Int? = null,
        @Query("part") part: Int? = null,
        @Query("rank") rank: Int? = null,
        @Query("ctime") ctime: Long? = null
    ): List<ImageDataBean>
}