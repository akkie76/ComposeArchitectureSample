package jp.co.compose.architecture.sample.domain.userInfo.module.store

import jp.co.compose.architecture.sample.app.module.ActionObserver
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction
import javax.inject.Inject

class UserInfoStore @Inject constructor() {

    var action: UserInfoAction = UserInfoAction.Initialize()
        private set

    private var observer: ActionObserver? = null

    fun register(actionObserver: ActionObserver) {
        observer = actionObserver
    }

    fun unRegister() {
        action = UserInfoAction.Initialize()
        observer = null
    }

    fun on(action: UserInfoAction) {
        this.action = action
        observer?.onDataChanged(action)
    }
}
