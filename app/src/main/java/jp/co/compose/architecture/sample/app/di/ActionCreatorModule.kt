package jp.co.compose.architecture.sample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.repository.module.action.RepositoryActionCreator
import jp.co.compose.architecture.sample.domain.repository.module.action.RepositoryActionCreatorImpl
import jp.co.compose.architecture.sample.domain.search.module.action.SearchActionCreator
import jp.co.compose.architecture.sample.domain.search.module.action.SearchActionCreatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ActionCreatorModule {

    @Binds
    abstract fun bindSearchActionCreator(
        searchActionCreatorImpl: SearchActionCreatorImpl
    ): SearchActionCreator

    @Binds
    abstract fun bindRepositoryActionCreator(
        repositoryActionCreatorImpl: RepositoryActionCreatorImpl
    ): RepositoryActionCreator
}
