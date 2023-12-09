package jp.co.compose.architecture.sample.domain.userInfo.data

import com.google.gson.annotations.SerializedName

data class RepositoryInfo(
    val name: String,
    val description: String?,
    @SerializedName("html_url")
    val url: String,
    val fork: Boolean,
    val language: String?,
    @SerializedName("stargazers_count")
    var stars: Int
) {
    val displayDescription: String
        get() = description ?: "None Description"

    val displayLanguage: String
        get() = language ?: ""

    companion object {
        fun List<RepositoryInfo>.filterNonForkedRepos() = this.filter { !it.fork }
    }
}
