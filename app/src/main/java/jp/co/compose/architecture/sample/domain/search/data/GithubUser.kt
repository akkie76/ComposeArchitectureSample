package jp.co.compose.architecture.sample.domain.search.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubUser(
    val id: Int,
    val login: String,
    val name: String?,
    @Json(name = "avatar_url")
    var avatarUrl: String
)
