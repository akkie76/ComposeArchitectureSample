package jp.co.compose.architecture.sample.app.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import jp.co.compose.architecture.sample.R
import my.nanihadesuka.compose.LazyColumnScrollbar

@Composable
fun Scrollbar(
    listState: LazyListState,
    content: @Composable () -> Unit
) {
    LazyColumnScrollbar(
        listState = listState,
        padding = dimensionResource(id = R.dimen.small_space),
        thumbColor = MaterialTheme.colorScheme.outlineVariant
    ) {
        content()
    }
}
