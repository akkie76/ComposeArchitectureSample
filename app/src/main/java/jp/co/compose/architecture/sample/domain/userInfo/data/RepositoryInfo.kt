package jp.co.compose.architecture.sample.domain.userInfo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryInfo(
    val name: String,
    val description: String?,
    @Json(name = "html_url")
    val url: String,
    val fork: Boolean,
    val language: String?,
    @Json(name = "stargazers_count")
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
