package jp.co.compose.architecture.sample.domain.userInfo.data

import jp.co.compose.architecture.sample.domain.search.data.GithubUser

interface UserInfoRepository {

    suspend fun fetchUserDetail(login: String): GithubUser

    suspend fun fetchFollowersCount(login: String): Int

    suspend fun fetchFollowingCount(login: String): Int
}
