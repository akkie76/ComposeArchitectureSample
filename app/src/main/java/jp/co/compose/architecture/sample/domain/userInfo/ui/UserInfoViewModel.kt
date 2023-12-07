package jp.co.compose.architecture.sample.domain.userInfo.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoActionCreator
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val repositoryActionCreator: UserInfoActionCreator
) : ViewModel() {

    fun onFetchUserInfo() {
    }

    fun onLaunchBrowser(activity: Activity, url: String) {
        repositoryActionCreator.launchBrowser(activity, url)
    }
}
