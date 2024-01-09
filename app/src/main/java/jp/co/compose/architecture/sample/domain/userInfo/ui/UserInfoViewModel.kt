package jp.co.compose.architecture.sample.domain.userInfo.ui

import android.app.Activity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.compose.architecture.sample.app.module.Action
import jp.co.compose.architecture.sample.app.module.ActionObserver
import jp.co.compose.architecture.sample.domain.navArgs
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoActionCreator
import jp.co.compose.architecture.sample.domain.userInfo.module.store.UserInfoStore
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userInfoActionCreator: UserInfoActionCreator,
    private val store: UserInfoStore
) : ViewModel(), ActionObserver {

    private val githubUser = savedStateHandle.navArgs<GithubUser>()

    private val _uiState = mutableStateOf(store.action)
    val uiState: State<UserInfoAction>
        get() = _uiState

    fun onCreate() {
        store.register(this)
    }

    fun onDispose() {
        store.unRegister()
    }

    suspend fun onFetchUserInfo() {
        userInfoActionCreator.fetchUserInfo(githubUser.login)
    }

    suspend fun onRetry() {
        userInfoActionCreator.retry(githubUser.login)
    }

    fun onLaunchBrowser(activity: Activity, url: String) {
        userInfoActionCreator.launchBrowser(activity, url)
    }

    override fun <T> onDataChanged(action: Action<T>) {
        _uiState.value = action as UserInfoAction
    }
}
