package com.ganeevrm.android.infoapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.ganeevrm.android.infoapps.navigation.AppNavGraph
import com.ganeevrm.android.infoapps.ui.app_detail.AppDetailViewModel
import com.ganeevrm.android.infoapps.ui.app_list.AppListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val appListViewModel: AppListViewModel by viewModels()
    private val appDetailViewModel: AppDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavGraph(navController, appListViewModel, appDetailViewModel)
        }
    }
}