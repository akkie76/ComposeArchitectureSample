package jp.co.compose.architecture.sample.domain.userInfo.data

import jp.co.compose.architecture.sample.app.data.GithubApiService
import javax.inject.Inject

interface UserInfoRepository {

    suspend fun fetchUserDetail(login: String): GithubUserInfo

    suspend fun fetchRepositories(login: String): List<RepositoryInfo>
}

class UserInfoRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService
) : UserInfoRepository {

    override suspend fun fetchUserDetail(login: String): GithubUserInfo {
        return githubApiService.users(login)
    }

    override suspend fun fetchRepositories(login: String): List<RepositoryInfo> {
        return githubApiService.repos(login)
    }
}
