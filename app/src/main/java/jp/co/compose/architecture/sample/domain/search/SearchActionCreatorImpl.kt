package jp.co.compose.architecture.sample.domain.search

import javax.inject.Inject

class SearchActionCreatorImpl @Inject constructor(
    private val dispatcher: SearchDispatcher
) : SearchActionCreator {

    override suspend fun searchRepository() {
        dispatcher.dispatch()
    }
}
