package jp.co.compose.architecture.sample.domain.userInfo.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import jp.co.compose.architecture.sample.R
import jp.co.compose.architecture.sample.app.ui.Scrollbar
import jp.co.compose.architecture.sample.domain.userInfo.data.UserInfo

@Composable
fun RepositoryColumn(
    modifier: Modifier = Modifier,
    userInfo: UserInfo,
    onBackClick: () -> Unit = {},
    onSelect: (String) -> Unit = {}
) {
    val listState = rememberLazyListState()

    Scrollbar(listState = listState) {
        LazyColumn(
            modifier = modifier,
            state = listState
        ) {
            item {
                GithubUserContent(
                    githubUser = userInfo.githubUserInfo,
                    onBackClick = onBackClick
                )
            }
            items(userInfo.repositories) { repository ->
                RepositoryCard(
                    repository = repository,
                    onSelect = onSelect
                )
            }
            item {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_space)))
            }
        }
    }
}
