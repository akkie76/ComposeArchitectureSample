package jp.co.compose.architecture.sample.domain.search.data

import com.google.gson.annotations.SerializedName

data class GithubSearchUsersResponse(
    @field: SerializedName("total_count") val totalCount: Int,
    val items: List<GithubUser>
)
