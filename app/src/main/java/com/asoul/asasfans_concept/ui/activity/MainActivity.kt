package com.asoul.asasfans_concept.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.asoul.asasfans_concept.AsApplication
import com.asoul.asasfans_concept.R
import com.asoul.asasfans_concept.databinding.ActivityMainBinding
import com.asoul.asasfans_concept.ui.fragment.FanArtFragment
import com.asoul.asasfans_concept.ui.fragment.RecordFragment
import com.asoul.asasfans_concept.ui.fragment.ToolsFragment
import com.asoul.asasfans_concept.ui.fragment.VideoFragment
import com.asoul.asasfans_concept.ui.viewmodel.MainViewModel
import com.asoul.asasfans_concept.utils.localVersionCode
import com.asoul.asasfans_concept.utils.showShortToast
import com.asoul.asasfans_concept.utils.toVersionCode
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 08:32
 * @Description : Description...
 */
class MainActivity : AppCompatActivity() {

    companion object {
        private const val VIDEO_FRAGMENT = 0
        private const val FAN_ART_FRAGMENT = 1
        private const val RECORD_FRAGMENT = 2
        private const val TOOLS_FRAGMENT = 3
    }

    private var lastBackPress = 0L
    private var lastSelectedPosition = 0
    private val fragments = Array(4) {
        when (it) {
            0 -> VideoFragment()
            1 -> FanArtFragment()
            2 -> RecordFragment()
            3 -> ToolsFragment()
            else -> throw IndexOutOfBoundsException("Out of bounds!")
        }
    }

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNav()

        lifecycleScope.launchWhenCreated {
            if (AsApplication.showUpdateNotification) {
                viewModel.latestVersion.collect { result ->
                    val versionString = result.getOrNull()
                    if (versionString != null) {
                        val latestVersion = versionString.name.substringAfter('v')
                        if (latestVersion.toVersionCode() > localVersionCode) {
                            MaterialAlertDialogBuilder(this@MainActivity)
                                .setTitle("新版本提醒")
                                .setMessage(versionString.body)
                                .setPositiveButton("去下载", null)
                                .setNegativeButton("忽略", null)
                                .show()
                        }
                    } else {
                        result.exceptionOrNull()?.printStackTrace()
                        "获取最新版本号失败".showShortToast()
                    }
                }
                AsApplication.showUpdateNotification = false
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val currentFragmentId = navController.currentDestination?.id
        if (currentFragmentId != null && currentFragmentId == R.id.video_fragment) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    exit()
                    return false
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun exit() {
        if ((System.currentTimeMillis() - lastBackPress) > 2000) {
            "再按一次退出".showShortToast()
            lastBackPress = System.currentTimeMillis()
        } else {
            finish()
        }
    }

    private fun initBottomNav() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }
}