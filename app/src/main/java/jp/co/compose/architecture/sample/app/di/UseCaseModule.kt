package jp.co.compose.architecture.sample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.search.usecase.SearchUsersUseCase
import jp.co.compose.architecture.sample.domain.search.usecase.SearchUsersUseCaseImpl
import jp.co.compose.architecture.sample.domain.userInfo.usecase.GetUserInfoUseCase
import jp.co.compose.architecture.sample.domain.userInfo.usecase.GetUserInfoUseCaseImpl
import jp.co.compose.architecture.sample.domain.userInfo.usecase.ProvideBrowserUseCase
import jp.co.compose.architecture.sample.domain.userInfo.usecase.ProvideBrowserUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindSearchUsersUseCase(
        searchUsersUseCaseImpl: SearchUsersUseCaseImpl
    ): SearchUsersUseCase

    @Binds
    abstract fun bindGetUserInfoUseCase(
        getUserInfoUseCaseImpl: GetUserInfoUseCaseImpl
    ): GetUserInfoUseCase

    @Binds
    abstract fun bindProvideBrowserUseCase(
        provideBrowserUseCaseImpl: ProvideBrowserUseCaseImpl
    ): ProvideBrowserUseCase
}
