package jp.co.compose.architecture.sample.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import jp.co.compose.architecture.sample.R

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    message: String = ""
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)
        CircularProgressIndicator(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.progress_padding))
        )
    }
}

@Preview
@Composable
private fun PreviewProgressIndicator() {
    Surface {
        ProgressIndicator(message = "Loading")
    }
}
