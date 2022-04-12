package com.asoul.asasfans_concept.logic.network

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 15:42
 * @Description : Description...
 */
interface VideoOldService {

    @GET("AsoulRT-FanArt")
    suspend fun getFanArtVideo(@Query("page") page: Int)

    @GET("AsoulRT-Cut")
    suspend fun getCutVideo(@Query("page") page: Int)

    @GET("AsoulPudateVedio")
    suspend fun getLatestVideo(@Query("page") page: Int)

    @GET("AsoulMostViewVedio")
    suspend fun getMostViewVideo(@Query("page") page: Int)
}