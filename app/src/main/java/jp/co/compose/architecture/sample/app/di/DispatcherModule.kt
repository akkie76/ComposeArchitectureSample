package jp.co.compose.architecture.sample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.search.SearchDispatcher
import jp.co.compose.architecture.sample.domain.search.SearchDispatcherImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindSearchDispatcher(
        searchDispatcher: SearchDispatcherImpl
    ): SearchDispatcher
}
