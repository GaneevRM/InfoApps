package com.ganeevrm.android.infoapps.ui.app_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ganeevrm.android.infoapps.domain.AppInfo


@Composable
fun AppListScreen(
    apps: List<AppInfo>,
    isLoading: Boolean,
    onAppClick: (AppInfo) -> Unit
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(WindowInsets.systemBars.asPaddingValues())
        ) {
            items(apps) { app ->
                ListItem(
                    headlineContent = { Text(app.appName) },
                    supportingContent = { Text(app.packageName) },
                    modifier = Modifier.clickable { onAppClick(app) }
                )
            }
        }
    }
}