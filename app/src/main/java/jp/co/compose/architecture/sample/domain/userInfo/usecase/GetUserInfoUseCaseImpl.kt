package jp.co.compose.architecture.sample.domain.userInfo.usecase

import jp.co.compose.architecture.sample.domain.userInfo.data.DisplayGithubUser
import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) : GetUserInfoUseCase {

    override suspend fun fetchUserInfo(login: String): UserInfo {
        val displayUser = createDisplayUser(login)
        return UserInfo(displayUser)
    }

    private suspend fun createDisplayUser(login: String): DisplayGithubUser {
        val userDetail = userInfoRepository.fetchUserDetail(login)
        return DisplayGithubUser(
            login = userDetail.login,
            name = userDetail.name,
            avatarUrl = userDetail.avatarUrl,
            followers = userInfoRepository.fetchFollowersCount(login),
            following = userInfoRepository.fetchFollowingCount(login)
        )
    }
}
