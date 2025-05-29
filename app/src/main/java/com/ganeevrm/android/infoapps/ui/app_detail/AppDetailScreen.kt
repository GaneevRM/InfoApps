package com.ganeevrm.android.infoapps.ui.app_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ganeevrm.android.infoapps.domain.AppInfo

@Composable
fun AppDetailScreen(
    app: AppInfo,
    checksum: String?,
    onLaunchApp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        Text("Название: ${app.appName}")
        Text("Пакет: ${app.packageName}")
        Text("Версия: ${app.versionName}")

        if (checksum.isNullOrEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Подсчёт SHA-1...")
            }
        } else {
            Text("SHA-1: $checksum")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onLaunchApp) {
            Text("Открыть приложение")
        }
    }
}