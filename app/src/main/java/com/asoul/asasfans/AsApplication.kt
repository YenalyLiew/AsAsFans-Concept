package com.asoul.asasfans

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 08:28
 * @Description : Description...
 */
class AsApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        var showUpdateNotification = true

        const val BILI_PACKAGE_NAME = "tv.danmaku.bili"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}