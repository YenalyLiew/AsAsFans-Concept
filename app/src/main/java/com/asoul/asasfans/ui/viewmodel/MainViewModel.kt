package com.asoul.asasfans.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.asoul.asasfans.logic.Repository

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 17:27
 * @Description : Description...
 */
class MainViewModel : ViewModel() {
    val latestVersion = Repository.latestVersion
}