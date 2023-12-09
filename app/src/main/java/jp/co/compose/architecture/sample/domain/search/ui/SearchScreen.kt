package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.paging.compose.collectAsLazyPagingItems
import jp.co.compose.architecture.sample.app.ui.ErrorContent
import jp.co.compose.architecture.sample.app.ui.ProgressIndicator
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction
import jp.co.compose.architecture.sample.domain.search.ui.component.GithubUserColumn
import jp.co.compose.architecture.sample.domain.search.ui.component.InitialContent
import jp.co.compose.architecture.sample.domain.search.ui.component.SearchBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigate: (GithubUser) -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                viewModel.onCreate()
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            viewModel.onDispose()
            lifecycle.removeObserver(observer)
        }
    }

    val state by viewModel.uiState
    var searchQuery by remember { mutableStateOf(viewModel.query) }

    Scaffold(
        topBar = {
            SearchBar(
                text = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue
                    viewModel.onSearchQueryChange(searchQuery)
                },
                onClickLeadingIcon = { newValue ->
                    searchQuery = newValue
                    viewModel.onSearchQueryChange(newValue)
                }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            val pagingItems = viewModel.users.collectAsLazyPagingItems()
            val loadStates = pagingItems.loadState.source

            LaunchedEffect(loadStates) {
                viewModel.onUpdateLoadState(loadStates.refresh)
            }

            if (searchQuery.isEmpty()) {
                InitialContent()
                return@Surface
            }

            when (state) {
                is SearchAction.NotLoading -> {
                    GithubUserColumn(pagingItems) { githubUser ->
                        onNavigate(githubUser)
                    }
                }
                is SearchAction.Loading -> {
                    ProgressIndicator()
                }
                is SearchAction.Error -> {
                    val errorState = state as SearchAction.Error
                    ErrorContent(
                        message = errorState.message,
                        onRetry = {
                            searchQuery = ""
                            viewModel.onRefresh(searchQuery)
                        }
                    )
                }
            }
        }
    }
}
