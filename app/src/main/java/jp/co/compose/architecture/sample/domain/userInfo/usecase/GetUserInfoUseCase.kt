package jp.co.compose.architecture.sample.domain.userInfo.usecase

import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfo

interface GetUserInfoUseCase {

    suspend fun fetchUserInfo(login: String): UserInfo
}
