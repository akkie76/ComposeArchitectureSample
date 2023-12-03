package jp.co.compose.architecture.sample.domain.repository.usecase

import android.app.Activity

interface ProvideBrowserUseCase {

    fun launch(activity: Activity, url: String)
}
