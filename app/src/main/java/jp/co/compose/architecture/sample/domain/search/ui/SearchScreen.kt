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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import jp.co.compose.architecture.sample.app.ui.ErrorContent
import jp.co.compose.architecture.sample.app.ui.ProgressIndicator
import jp.co.compose.architecture.sample.domain.destinations.UserInfoScreenDestination
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction
import jp.co.compose.architecture.sample.domain.search.ui.component.GithubUserColumn
import jp.co.compose.architecture.sample.domain.search.ui.component.InitialContent
import jp.co.compose.architecture.sample.domain.search.ui.component.SearchBar

@RootNavGraph(start = true)
@Destination
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
) {
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

    SearchScreenContent(
        state = state,
        pagingItems = viewModel.users.collectAsLazyPagingItems(),
        query = viewModel.query,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
        onUpdateLoadState = { viewModel.onUpdateLoadState(it) },
        onNavigate = { navigator.navigate(UserInfoScreenDestination(it)) },
        onRefresh = { viewModel.onRefresh(it) }
    )
}

@Composable
private fun SearchScreenContent(
    state: SearchAction,
    pagingItems: LazyPagingItems<GithubUser>,
    query: String,
    onSearchQueryChange: (String) -> Unit = {},
    onUpdateLoadState: (LoadState) -> Unit = {},
    onNavigate: (GithubUser) -> Unit = {},
    onRefresh: (String) -> Unit = {}
) {
    val loadStates = pagingItems.loadState.source
    var searchQuery by remember { mutableStateOf(query) }

    LaunchedEffect(loadStates) {
        onUpdateLoadState(loadStates.refresh)
    }

    Scaffold(
        topBar = {
            SearchBar(
                text = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue
                    onSearchQueryChange(searchQuery)
                },
                onClickLeadingIcon = { newValue ->
                    searchQuery = newValue
                    onSearchQueryChange(newValue)
                }
            )
        }
    ) { paddingValues ->
        if (searchQuery.isEmpty()) {
            InitialContent(modifier = Modifier.padding(paddingValues))
            return@Scaffold
        }

        when (state) {
            is SearchAction.NotLoading -> {
                GithubUserColumn(pagingItems = pagingItems) { githubUser ->
                    onNavigate(githubUser)
                }
            }

            is SearchAction.Loading -> {
                ProgressIndicator()
            }

            is SearchAction.Error -> {
                ErrorContent(
                    message = state.message,
                    onRetry = {
                        searchQuery = ""
                        onRefresh(searchQuery)
                    }
                )
            }
        }
    }
}
