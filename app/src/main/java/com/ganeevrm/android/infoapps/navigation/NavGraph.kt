package com.ganeevrm.android.infoapps.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ganeevrm.android.infoapps.ui.app_detail.AppDetailScreen
import com.ganeevrm.android.infoapps.ui.app_detail.AppDetailViewModel
import com.ganeevrm.android.infoapps.ui.app_list.AppListScreen
import com.ganeevrm.android.infoapps.ui.app_list.AppListViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    appListViewModel: AppListViewModel,
    appDetailViewModel: AppDetailViewModel
) {
    val context = LocalContext.current

    val apps by appListViewModel.apps.collectAsState()
    val isLoading by appListViewModel.isLoading.collectAsState()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            AppListScreen(apps = apps, isLoading = isLoading) { app ->
                navController.navigate("detail/${app.packageName}")
            }
        }
        composable("detail/{packageName}") { backStackEntry ->
            val packageName = backStackEntry.arguments?.getString("packageName")
            val app = apps.find { it.packageName == packageName } ?: return@composable

            LaunchedEffect(app.apkPath) {
                appDetailViewModel.loadChecksum(app.apkPath)
            }

            val checksum by appDetailViewModel.checksum.collectAsState()

            AppDetailScreen(app = app, checksum = checksum) {
                val launchIntent = context.packageManager.getLaunchIntentForPackage(app.packageName)
                launchIntent?.let { context.startActivity(it) }
            }
        }
    }
}