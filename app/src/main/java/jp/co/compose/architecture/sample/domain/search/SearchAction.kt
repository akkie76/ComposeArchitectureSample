package jp.co.compose.architecture.sample.domain.search

import jp.co.compose.architecture.sample.app.Action

sealed class SearchAction<out T> : Action<T> {

    class Initialize(override val data: String) : SearchAction<String>()

    class Loaded(override val data: String) : SearchAction<String>()
}
