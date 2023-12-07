package jp.co.compose.architecture.sample.domain.search.data

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val id: Int,
    val login: String,
    val name: String?,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("followers_url")
    var followersUrl: String,
    @SerializedName("following_url")
    var followingUrl: String
)
