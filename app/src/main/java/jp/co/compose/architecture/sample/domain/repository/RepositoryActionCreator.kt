package jp.co.compose.architecture.sample.domain.repository

import android.app.Activity

interface RepositoryActionCreator {

    fun launchBrowser(activity: Activity, url: String)
}
