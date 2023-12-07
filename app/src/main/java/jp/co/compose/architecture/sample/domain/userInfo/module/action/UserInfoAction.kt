package jp.co.compose.architecture.sample.domain.userInfo.module.action

import jp.co.compose.architecture.sample.app.Action
import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfo

sealed class UserInfoAction(
    override val data: UserInfo
) : Action<UserInfo> {

    class Initialize(
        override val data: UserInfo = UserInfo.Initial
    ) : UserInfoAction(
        data = data
    )

    class Ready(
        override val data: UserInfo
    ) : UserInfoAction(
        data = data
    )

    class Error(
        override val data: UserInfo = UserInfo.Initial
    ) : UserInfoAction(
        data = data
    )
}
