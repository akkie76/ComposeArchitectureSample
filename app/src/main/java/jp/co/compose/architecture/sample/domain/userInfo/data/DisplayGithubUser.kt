package jp.co.compose.architecture.sample.domain.userInfo.data

data class DisplayGithubUser(
    private val login: String = "",
    private val name: String? = null,
    val avatarUrl: String = "",
    val followers: Int = 0,
    val following: Int = 0
) {
    val userName: String
        get() = login

    val fullName: String
        get() = name ?: "Undefined User"
}
