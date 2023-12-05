package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import jp.co.compose.architecture.sample.domain.search.data.GithubUser

@Composable
fun UsersColumn(
    pagingItems: LazyPagingItems<GithubUser>
) {
    // TODO: Scrollbarを追加する
    LazyColumn {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey()
        ) { index ->
            val user = pagingItems[index] ?: return@items
            Text(user.login)
        }
    }
}
