package jp.co.compose.architecture.sample.domain.search

import javax.inject.Inject

class SearchDispatcherImpl @Inject constructor(
    private val store: SearchStore
) : SearchDispatcher {

    override fun dispatch() {
        store.on(SearchAction.Loaded("loaded"))
    }
}
