package jp.co.compose.architecture.sample.domain.userInfo.data

data class UserInfo(
    val githubUserInfo: GithubUserInfo,
    val repositories: List<RepositoryInfo> = emptyList()
) {
    companion object {
        val Initial = UserInfo(GithubUserInfo())
    }
}
