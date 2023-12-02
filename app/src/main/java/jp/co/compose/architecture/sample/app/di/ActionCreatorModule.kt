package jp.co.compose.architecture.sample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.search.SearchActionCreator
import jp.co.compose.architecture.sample.domain.search.SearchActionCreatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ActionCreatorModule {

    @Binds
    abstract fun bindSearchActionCreator(
        searchActionCreatorImpl: SearchActionCreatorImpl
    ): SearchActionCreator
}
