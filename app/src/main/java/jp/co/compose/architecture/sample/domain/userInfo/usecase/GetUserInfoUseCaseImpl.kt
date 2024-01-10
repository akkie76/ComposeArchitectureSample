package jp.co.compose.architecture.sample.domain.userInfo.usecase

import jp.co.compose.architecture.sample.domain.userInfo.data.GithubUserInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.RepositoryInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.RepositoryInfo.Companion.filterNonForkedRepos
import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfoRepository
import javax.inject.Inject

/**
 * ユーザ詳細情報を取得するユースケースのインターフェース
 */
interface GetUserInfoUseCase {

    suspend fun fetchUserInfo(login: String): UserInfo
}

/**
 * ユーザ詳細情報を取得するユースケースの実測クラス
 *
 * @param userInfoRepository ユーザ情報を取得するリポジトリ
 */
class GetUserInfoUseCaseImpl @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) : GetUserInfoUseCase {

    /**
     * ユーザ詳細情報を取得する
     *
     * @param login ログイン名
     * @return ユーザ詳細情報
     */
    override suspend fun fetchUserInfo(login: String): UserInfo {
        val githubUserInfo = fetchGithubUserInfo(login)
        val repositories = fetchNonForkedRepositories(login)
        return UserInfo(githubUserInfo, repositories)
    }

    /**
     * GitHubユーザ詳細情報を取得する
     *
     * @param login ログイン名
     * @return GitHubユーザ詳細情報
     */
    private suspend fun fetchGithubUserInfo(login: String): GithubUserInfo {
        return userInfoRepository.fetchUserDetail(login)
    }

    /**
     * フォークされていないリポジトリ情報を取得する
     *
     * @param login ログイン名
     * @return リポジトリ情報一覧
     */
    private suspend fun fetchNonForkedRepositories(login: String): List<RepositoryInfo> {
        return userInfoRepository.fetchRepositories(login).filterNonForkedRepos()
    }
}
