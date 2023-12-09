package jp.co.compose.architecture.sample.app.module

interface Action<out T> {
    val data: T
}
