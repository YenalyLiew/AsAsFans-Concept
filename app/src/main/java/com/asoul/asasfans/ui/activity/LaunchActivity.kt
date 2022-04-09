package com.asoul.asasfans.ui.activity

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.asoul.asasfans.R
import com.asoul.asasfans.databinding.ActivityLaunchBinding
import com.asoul.asasfans.logic.dao.DBOpenHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 15:58
 * @Description : Description...
 */
class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbOpenHelper = DBOpenHelper("blackList.db", null, 1)
        dbOpenHelper.close()

        // Android 12 自带开屏，所以不显示此Activity的View.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            next()
            return
        }

        setContentView(R.layout.activity_launch)
        handler.postDelayed({
            handler.sendEmptyMessage(0)
        }, 500)
    }

    private val handler = Handler(Looper.getMainLooper()) {
        next()
        true
    }

    private fun next() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}