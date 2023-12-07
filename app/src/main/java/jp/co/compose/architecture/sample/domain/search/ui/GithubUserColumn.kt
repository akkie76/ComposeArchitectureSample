package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import jp.co.compose.architecture.sample.domain.search.data.GithubUser

@Composable
fun GithubUserColumn(
    pagingItems: LazyPagingItems<GithubUser>,
    onSelected: (GithubUser) -> Unit
) {
    val focusManager = LocalFocusManager.current
    // TODO: Add Scrollbar
    LazyColumn {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey()
        ) { index ->
            val user = pagingItems[index] ?: return@items
            // NOTE: https://m3.material.io/components/lists/specs
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = true,
                        onClick = {
                            focusManager.clearFocus()
                            onSelected(user)
                        }
                    )
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                AsyncImage(
                    model = "", // user.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
