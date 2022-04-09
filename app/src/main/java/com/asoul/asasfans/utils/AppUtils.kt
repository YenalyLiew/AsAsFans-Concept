package com.asoul.asasfans.utils

import androidx.core.content.pm.PackageInfoCompat
import com.asoul.asasfans.AsApplication

fun String.toVersionCode(): Int {
    val versionSplit = this.split('.')
    return versionSplit[0].toInt() * 100 + versionSplit[1].toInt() * 10 + versionSplit[2].toInt()
}

val localVersionCode: Long
    get() {
        val packageInfo =
            AsApplication.context.packageManager.getPackageInfo(
                AsApplication.context.packageName,
                0
            )
        return PackageInfoCompat.getLongVersionCode(packageInfo)
    }