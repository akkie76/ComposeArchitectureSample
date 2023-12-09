package jp.co.compose.architecture.sample.domain.userInfo.module.action

import android.app.Activity
import jp.co.compose.architecture.sample.domain.userInfo.module.dispatcher.UserInfoDispatcher
import jp.co.compose.architecture.sample.domain.userInfo.usecase.GetUserInfoUseCase
import jp.co.compose.architecture.sample.domain.userInfo.usecase.ProvideBrowserUseCase
import javax.inject.Inject

class UserInfoActionCreatorImpl @Inject constructor(
    private val dispatcher: UserInfoDispatcher,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val provideBrowserUseCase: ProvideBrowserUseCase
) : UserInfoActionCreator {

    override suspend fun fetchUserInfo(login: String) {
        try {
            val userInfo = getUserInfoUseCase.fetchUserInfo(login)
            dispatcher.dispatch(UserInfoAction.Ready(userInfo))
        } catch (e: Exception) {
            dispatcher.dispatch(UserInfoAction.Error(message = e.message))
        }
    }

    override suspend fun retry(login: String) {
        dispatcher.dispatch(UserInfoAction.Initialize())
        fetchUserInfo(login)
    }

    override fun launchBrowser(activity: Activity, url: String) {
        provideBrowserUseCase.launch(activity, url)
    }
}
