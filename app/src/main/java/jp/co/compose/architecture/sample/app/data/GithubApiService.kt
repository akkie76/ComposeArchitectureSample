package jp.co.compose.architecture.sample.app.data

import jp.co.compose.architecture.sample.domain.search.data.GithubSearchUsersResponse
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.userInfo.data.GithubUserInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.RepositoryInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    /**
     * GitHubユーザを検索する
     *
     * @param query 検索クエリ
     * @param page ページ数
     * @param perPage ページごと取得数
     * @return [GithubSearchUsersResponse] の一覧
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GithubSearchUsersResponse

    /**
     * GitHubユーザの詳細情報を取得する
     *
     * @param login ログイン名
     * @return [GithubUser]
     */
    @GET("users/{login}")
    suspend fun users(
        @Path("login") login: String
    ): GithubUserInfo

    /**
     * リポジトリ一覧を取得する
     *
     * @param login ログイン名
     * @return [RepositoryInfo] の一覧
     */
    @GET("users/{login}/repos")
    suspend fun repos(
        @Path("login") login: String
    ): List<RepositoryInfo>
}
