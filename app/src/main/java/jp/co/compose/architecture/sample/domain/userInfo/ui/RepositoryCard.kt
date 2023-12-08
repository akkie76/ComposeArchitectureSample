package jp.co.compose.architecture.sample.domain.userInfo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.compose.architecture.sample.domain.userInfo.data.RepositoryInfo

@Composable
fun RepositoryCard(
    repository: RepositoryInfo,
    onSelected: (String) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(start = 16.dp)
            .padding(end = 16.dp)
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .selectable(
                selected = true,
                onClick = {
                    onSelected(repository.url)
                }
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = repository.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = repository.displayDescription,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = repository.displayLanguage,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "⭐ ${repository.stars}",
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewRepositoryCard() {
    RepositoryCard(
        repository = RepositoryInfo(
            name = "kotlin/kotlinx.coroutines",
            description = "Library support for Kotlin coroutines",
            url = "",
            fork = false,
            language = "Kotlin",
            stars = 30500
        )
    )
}
