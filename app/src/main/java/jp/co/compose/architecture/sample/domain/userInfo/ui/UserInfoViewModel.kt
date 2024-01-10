package jp.co.compose.architecture.sample.domain.userInfo.ui

import android.app.Activity
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.compose.architecture.sample.app.module.Action
import jp.co.compose.architecture.sample.app.module.ActionObserver
import jp.co.compose.architecture.sample.domain.navArgs
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoActionCreator
import jp.co.compose.architecture.sample.domain.userInfo.module.store.UserInfoStore
import kotlinx.coroutines.launch
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

    /**
     * Constant for onCreate event of the [LifecycleOwner].
     */
    fun onCreate() {
        store.register(this)
    }

    /**
     * Provided from the [DisposableEffect]
     */
    fun onDispose() {
        store.unRegister()
    }

    /**
     * ユーザ詳細情報を取得する
     */
    fun onFetchUserInfo() = viewModelScope.launch {
        userInfoActionCreator.fetchUserInfo(githubUser.login)
    }

    /**
     * ユーザ詳細情報を再取得する
     */
    fun onRetry() = viewModelScope.launch {
        userInfoActionCreator.retry(githubUser.login)
    }

    /**
     * リポジトリ詳細情報をカスタムタブで開く
     *
     * @param activity Activity
     * @param url リポジトリ詳細情報URL
     */
    fun onLaunchBrowser(activity: Activity, url: String) {
        userInfoActionCreator.launchBrowser(activity, url)
    }

    /**
     * ステートを更新する
     *
     * @param action アクション
     */
    override fun <T> onDataChanged(action: Action<T>) {
        _uiState.value = action as UserInfoAction
    }
}
