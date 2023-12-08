package jp.co.compose.architecture.sample.domain.userInfo.data

interface UserInfoRepository {

    suspend fun fetchUserDetail(login: String): GithubUserInfo

    suspend fun fetchRepositories(login: String): List<RepositoryInfo>
}
