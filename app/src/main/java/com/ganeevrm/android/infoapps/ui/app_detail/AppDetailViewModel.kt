package com.ganeevrm.android.infoapps.ui.app_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ganeevrm.android.infoapps.data.AppRepository


@HiltViewModel
class AppDetailViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _checksum = MutableStateFlow<String?>(null)
    val checksum: StateFlow<String?> = _checksum

    fun loadChecksum(apkPath: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _checksum.value = null
            _checksum.value = repository.getApkChecksum(apkPath)
        }
    }
}