package jp.co.compose.architecture.sample.domain.userInfo.data

data class UserInfo(
    val displayUserInfo: DisplayGithubUser
) {
    companion object {
        val Initial = UserInfo(DisplayGithubUser())
    }
}
