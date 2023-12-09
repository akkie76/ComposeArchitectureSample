package jp.co.compose.architecture.sample.domain.userInfo.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import jp.co.compose.architecture.sample.R
import jp.co.compose.architecture.sample.domain.userInfo.data.GithubUserInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubUserContent(
    githubUser: GithubUserInfo,
    onBackClick: () -> Unit = {}
) {
    Surface {
        Column {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.large_space))
                    .padding(bottom = dimensionResource(id = R.dimen.large_space)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = githubUser.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.github_user_image_size))
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = dimensionResource(id = R.dimen.large_space))
                ) {
                    Text(
                        text = githubUser.userName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = githubUser.fullName)
                    Row(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_space)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_group),
                            contentDescription = null
                        )
                        val follows = "${githubUser.followers} followers - ${githubUser.following} following"
                        Text(
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.small_space)),
                            text = follows
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewGithubUserContent() {
    val githubUser = GithubUserInfo(
        login = "akkie76",
        avatarUrl = "https://avatars.githubusercontent.com/u/11865114?v=4",
        following = 999,
        followers = 111
    )
    GithubUserContent(githubUser)
}
