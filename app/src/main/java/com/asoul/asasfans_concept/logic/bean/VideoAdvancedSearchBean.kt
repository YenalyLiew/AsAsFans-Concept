package com.asoul.asasfans_concept.logic.bean

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/09 009 16:01
 * @Description : Description...
 */
data class VideoAdvancedSearchBean(
    val code: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val page: Int,
        val numResults: Int,
        val result: List<Result>
    ) {
        data class Result(
            val aid: Long,
            val bvid: String,
            val coin: Int,
            val copyright: Int,
            val danmaku: Int,
            val desc: String,
            val duration: String,
            val face: String,
            val favorite: Int,
            val like: Int,
            val mid: Long,
            val name: String,
            val pic: String,
            val pubdate: Long,
            val reply: Int,
            val score: Int,
            val share: Int,
            val tag: String,
            val tid: Int,
            val title: String,
            val tname: String,
            val view: Int
        )
    }
}