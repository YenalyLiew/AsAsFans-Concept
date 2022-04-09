package com.asoul.asasfans.logic.network

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 09:30
 * @Description : Description...
 */
object AsNetwork {
    private const val GITHUB_BASE_URL =
        "https://api.github.com/"
    private const val ASOUL_VIDEO_OLD =
        "http://124.223.8.236:5200/"
    private const val ASOUL_VIDEO =
        "https://api.asasfans.asf.ink/"
    private const val ASOUL_FAN_ART =
        "https://api.asoul.cloud:8000/"

    val upgradeService = ApiFactory.create<UpgradeService>(GITHUB_BASE_URL)
    val videoOldService = ApiFactory.create<VideoOldService>(ASOUL_VIDEO_OLD)
    val videoService = ApiFactory.create<VideoService>(ASOUL_VIDEO)
    val fanArtService = ApiFactory.create<FanArtService>(ASOUL_FAN_ART)
}