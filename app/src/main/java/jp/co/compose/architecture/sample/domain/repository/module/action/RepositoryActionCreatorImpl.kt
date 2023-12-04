package jp.co.compose.architecture.sample.domain.repository.module.action

import android.app.Activity
import jp.co.compose.architecture.sample.domain.repository.usecase.ProvideBrowserUseCase
import javax.inject.Inject

class RepositoryActionCreatorImpl @Inject constructor(
    private val provideBrowserUseCase: ProvideBrowserUseCase
) : RepositoryActionCreator {

    override fun launchBrowser(activity: Activity, url: String) {
        provideBrowserUseCase.launch(activity, url)
    }
}
