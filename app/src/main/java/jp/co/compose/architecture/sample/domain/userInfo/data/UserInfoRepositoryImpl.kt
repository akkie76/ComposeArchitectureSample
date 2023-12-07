package jp.co.compose.architecture.sample.domain.userInfo.data

import jp.co.compose.architecture.sample.app.data.GithubApiService
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService
) : UserInfoRepository {

    override suspend fun fetchUserDetail(login: String): GithubUser {
        return githubApiService.users(login)
    }

    override suspend fun fetchFollowersCount(login: String): Int {
        return githubApiService.followers(login).size
    }

    override suspend fun fetchFollowingCount(login: String): Int {
        return githubApiService.following(login).size
    }
}
