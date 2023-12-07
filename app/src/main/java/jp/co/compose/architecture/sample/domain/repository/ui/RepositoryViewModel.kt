package jp.co.compose.architecture.sample.domain.repository.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.compose.architecture.sample.domain.repository.module.action.RepositoryActionCreator
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repositoryActionCreator: RepositoryActionCreator
) : ViewModel() {

    fun onFetchUserInfo() {
    }

    fun onLaunchBrowser(activity: Activity, url: String) {
        repositoryActionCreator.launchBrowser(activity, url)
    }
}
