package com.asoul.asasfans.logic.network

import com.asoul.asasfans.logic.bean.GithubVersionBean
import retrofit2.http.GET

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 09:02
 * @Description : Description...
 */
interface UpgradeService {
    @GET("repos/A-SoulFan/as-as-fans/releases/latest")
    suspend fun getLatestVersion(): GithubVersionBean
}