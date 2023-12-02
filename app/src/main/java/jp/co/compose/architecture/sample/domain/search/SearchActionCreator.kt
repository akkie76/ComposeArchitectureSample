package jp.co.compose.architecture.sample.domain.search

interface SearchActionCreator {

    suspend fun searchRepository()
}
