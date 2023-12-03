package jp.co.compose.architecture.sample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.repository.usecase.ProvideBrowserUseCase
import jp.co.compose.architecture.sample.domain.repository.usecase.ProvideBrowserUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindProvideBrowserUseCase(
        provideBrowserUseCaseImpl: ProvideBrowserUseCaseImpl
    ): ProvideBrowserUseCase
}
