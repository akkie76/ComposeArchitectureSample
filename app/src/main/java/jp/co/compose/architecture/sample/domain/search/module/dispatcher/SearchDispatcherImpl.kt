package jp.co.compose.architecture.sample.domain.search.module.dispatcher

import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction
import jp.co.compose.architecture.sample.domain.search.module.store.SearchStore
import javax.inject.Inject

interface SearchDispatcher {

    fun dispatch(searchAction: SearchAction)
}

class SearchDispatcherImpl @Inject constructor(
    private val store: SearchStore
) : SearchDispatcher {

    override fun dispatch(searchAction: SearchAction) {
        store.on(searchAction)
    }
}
