package jp.co.compose.architecture.sample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.search.data.SearchUsersRepository
import jp.co.compose.architecture.sample.domain.search.data.SearchUsersRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchUsersRepository(
        searchUsersRepositoryImpl: SearchUsersRepositoryImpl
    ): SearchUsersRepository
}
