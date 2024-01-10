package jp.co.compose.architecture.sample.domain.userInfo.module.action

import android.app.Activity
import jp.co.compose.architecture.sample.domain.userInfo.module.dispatcher.UserInfoDispatcher
import jp.co.compose.architecture.sample.domain.userInfo.usecase.GetUserInfoUseCase
import jp.co.compose.architecture.sample.domain.userInfo.usecase.ProvideBrowserUseCase
import javax.inject.Inject

interface UserInfoActionCreator {

    suspend fun fetchUserInfo(login: String)

    suspend fun retry(login: String)

    fun launchBrowser(activity: Activity, url: String)
}

class UserInfoActionCreatorImpl @Inject constructor(
    private val dispatcher: UserInfoDispatcher,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val provideBrowserUseCase: ProvideBrowserUseCase
) : UserInfoActionCreator {

    /**
     * ユーザ詳細情報を取得する
     *
     * @param login ログイン名
     */
    override suspend fun fetchUserInfo(login: String) {
        try {
            val userInfo = getUserInfoUseCase.fetchUserInfo(login)
            dispatcher.dispatch(UserInfoAction.Ready(userInfo))
        } catch (e: Exception) {
            dispatcher.dispatch(UserInfoAction.Error(message = e.message))
        }
    }

    /**
     * ユーザ情報を再取得する
     *
     * @param login ログイン名
     */
    override suspend fun retry(login: String) {
        dispatcher.dispatch(UserInfoAction.Initialize())
        fetchUserInfo(login)
    }

    /**
     * カスタムタブでブラウザを開く
     *
     * @param activity Activity
     * @param url リポジトリ情報のURL
     */
    override fun launchBrowser(activity: Activity, url: String) {
        provideBrowserUseCase.launch(activity, url)
    }
}
