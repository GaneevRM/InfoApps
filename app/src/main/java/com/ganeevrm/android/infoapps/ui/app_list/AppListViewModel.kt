package com.ganeevrm.android.infoapps.ui.app_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ganeevrm.android.infoapps.data.AppRepository
import com.ganeevrm.android.infoapps.domain.AppInfo


@HiltViewModel
class AppListViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps: StateFlow<List<AppInfo>> = _apps

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _apps.value = repository.getInstalledApps()
            _isLoading.value = false        }
    }
}