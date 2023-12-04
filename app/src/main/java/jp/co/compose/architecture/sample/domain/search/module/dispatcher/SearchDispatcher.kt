package jp.co.compose.architecture.sample.domain.search.module.dispatcher

import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction

interface SearchDispatcher {

    fun dispatch(searchAction: SearchAction)
}
