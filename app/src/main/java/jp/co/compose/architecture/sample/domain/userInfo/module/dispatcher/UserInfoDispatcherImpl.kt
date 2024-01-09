package jp.co.compose.architecture.sample.domain.userInfo.module.dispatcher

import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction
import jp.co.compose.architecture.sample.domain.userInfo.module.store.UserInfoStore
import javax.inject.Inject

interface UserInfoDispatcher {

    fun dispatch(userInfoAction: UserInfoAction)
}

class UserInfoDispatcherImpl @Inject constructor(
    private val store: UserInfoStore
) : UserInfoDispatcher {

    override fun dispatch(userInfoAction: UserInfoAction) {
        store.on(userInfoAction)
    }
}
