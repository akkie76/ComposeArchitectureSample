package jp.co.compose.architecture.sample.domain.userInfo.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.co.compose.architecture.sample.app.ui.ProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialContent(onBackClick: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()) {
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
        ProgressIndicator()
    }
}

@Preview
@Composable
private fun PreviewInitialContent() {
    Surface {
        InitialContent()
    }
}
