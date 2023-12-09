package jp.co.compose.architecture.sample.app.module

interface ActionObserver {
    fun <T> onDataChanged(action: Action<T>)
}
