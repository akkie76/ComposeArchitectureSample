package jp.co.compose.architecture.sample.domain.userInfo.module.action

import android.app.Activity

interface UserInfoActionCreator {

    suspend fun fetchUserInfo(login: String)

    fun launchBrowser(activity: Activity, url: String)
}
