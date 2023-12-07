package jp.co.compose.architecture.sample.domain.userInfo.module.action

import android.app.Activity
import jp.co.compose.architecture.sample.domain.userInfo.usecase.ProvideBrowserUseCase
import javax.inject.Inject

class UserInfoActionCreatorImpl @Inject constructor(
    private val provideBrowserUseCase: ProvideBrowserUseCase
) : UserInfoActionCreator {

    override fun launchBrowser(activity: Activity, url: String) {
        provideBrowserUseCase.launch(activity, url)
    }
}
