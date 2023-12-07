package jp.co.compose.architecture.sample.domain.userInfo.module.dispatcher

import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction

interface UserInfoDispatcher {

    fun dispatch(userInfoAction: UserInfoAction)
}
