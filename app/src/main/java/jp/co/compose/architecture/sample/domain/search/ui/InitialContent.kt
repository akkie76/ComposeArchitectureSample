package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import jp.co.compose.architecture.sample.R

@Composable
fun InitialContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(id = R.drawable.github_cat),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.github_icon_size))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.initial_content_spacer_height)))
        Text(
            text = stringResource(R.string.initial_message),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
