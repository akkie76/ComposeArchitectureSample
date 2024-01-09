package jp.co.compose.architecture.sample.domain.userInfo.usecase

import jp.co.compose.architecture.sample.domain.userInfo.data.GithubUserInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.RepositoryInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.RepositoryInfo.Companion.filterNonForkedRepos
import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfoRepository
import javax.inject.Inject

interface GetUserInfoUseCase {

    suspend fun fetchUserInfo(login: String): UserInfo
}

class GetUserInfoUseCaseImpl @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) : GetUserInfoUseCase {

    override suspend fun fetchUserInfo(login: String): UserInfo {
        val githubUserInfo = fetchGithubUserInfo(login)
        val repositories = fetchNonForkedRepositories(login)
        return UserInfo(githubUserInfo, repositories)
    }

    private suspend fun fetchGithubUserInfo(login: String): GithubUserInfo {
        return userInfoRepository.fetchUserDetail(login)
    }

    private suspend fun fetchNonForkedRepositories(login: String): List<RepositoryInfo> {
        return userInfoRepository.fetchRepositories(login).filterNonForkedRepos()
    }
}
