package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                AsyncImage(
                    model = user.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(32.dp)
                        .height(32.dp),
                    contentScale = ContentScale.Crop
                )
                Text(user.login)
            }
        }
    }
}
