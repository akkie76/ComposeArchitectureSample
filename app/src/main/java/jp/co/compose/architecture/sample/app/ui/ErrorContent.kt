package jp.co.compose.architecture.sample.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.compose.architecture.sample.R

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.error_message),
    onRetry: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.error_column_padding)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.error_spacer_height)))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = dimensionResource(id = R.dimen.error_title_font_size).value.sp),
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.error_title_height))
        )
        onRetry?.let { onClick ->
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.error_spacer_height)))
            Button(
                onClick = onClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewErrorContent() {
    Surface {
        ErrorContent(message = "Error Message")
    }
}
