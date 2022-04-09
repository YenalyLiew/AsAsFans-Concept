package com.asoul.asasfans.logic

import com.asoul.asasfans.logic.network.AsNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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
}