package jp.co.compose.architecture.sample.domain.search.data

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val id: Int,
    val login: String,
    val name: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
