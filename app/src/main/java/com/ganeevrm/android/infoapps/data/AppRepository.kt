package com.ganeevrm.android.infoapps.data

import android.annotation.SuppressLint
import android.content.Context
import com.ganeevrm.android.infoapps.domain.AppInfo
import java.io.FileInputStream
import java.security.MessageDigest

class AppRepository(private val context: Context) {
    @SuppressLint("QueryPermissionsNeeded")
    fun getInstalledApps(): List<AppInfo> {
        val pm = context.packageManager
        return pm.getInstalledPackages(0).map {
            AppInfo(
                appName = it.applicationInfo?.loadLabel(pm).toString(),
                packageName = it.packageName,
                versionName = it.versionName ?: "",
                apkPath = it.applicationInfo?.sourceDir ?: ""
            )
        }
    }

    fun getApkChecksum(path: String): String {
        val buffer = ByteArray(1024)
        val shaDigest = MessageDigest.getInstance("SHA-1")
        FileInputStream(path).use { fis ->
            var read: Int
            while (fis.read(buffer).also { read = it } != -1) {
                shaDigest.update(buffer, 0, read)
            }
        }
        return shaDigest.digest().joinToString("") { "%02x".format(it) }
    }
}