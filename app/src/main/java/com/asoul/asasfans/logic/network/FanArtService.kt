package com.asoul.asasfans.logic.network

import com.asoul.asasfans.logic.bean.ImageDataBean
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
        @Query("sort") sort: Int,
        @Query("part") part: Int,
        @Query("rank") rank: Int,
        @Query("ctime") ctime: Int
    ): ImageDataBean
}