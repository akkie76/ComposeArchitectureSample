package jp.co.compose.architecture.sample.domain.userInfo.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import jp.co.compose.architecture.sample.R
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction

@Composable
fun RepositoryColumn(
    state: UserInfoAction,
    onBackClick: () -> Unit = {},
    onSelected: (String) -> Unit = {}
) {
    LazyColumn {
        item {
            GithubUserContent(
                githubUser = state.data.githubUserInfo,
                onBackClick = onBackClick
            )
        }
        items(state.data.repositories) { repository ->
            RepositoryCard(
                repository = repository,
                onSelected = onSelected
            )
        }
        item {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_space)))
        }
    }
}
