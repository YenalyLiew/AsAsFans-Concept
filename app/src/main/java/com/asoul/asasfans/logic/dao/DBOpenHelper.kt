package com.asoul.asasfans.logic.dao

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.asoul.asasfans.AsApplication

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 08:32
 * @Description : Description...
 */
class DBOpenHelper(
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(AsApplication.context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql =
            "create table blackBvid(bvid TEXT primary key NOT NULL UNIQUE, PicUrl TEXT, Title TEXT, Duration integer, Author TEXT, ViewNum integer, LikeNum integer, Tname TEXT)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}