package jp.co.compose.architecture.sample.domain.search.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubSearchUsersResponse(
    @Json(name = "total_count") val totalCount: Int,
    val items: List<GithubUser>
)
