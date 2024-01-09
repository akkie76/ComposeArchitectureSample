package jp.co.compose.architecture.sample.domain.userInfo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubUserInfo(
    private val login: String = "",
    private val name: String? = null,
    @Json(name = "avatar_url")
    val avatarUrl: String = "",
    val followers: Int = 0,
    val following: Int = 0
) {
    val userName: String
        get() = login

    val fullName: String
        get() = name ?: "Undefined User"
}
