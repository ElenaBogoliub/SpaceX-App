package com.ebogoliub.spacex.inject

import com.ebogoliub.spacex.core.initializers.AppInitializer
import com.ebogoliub.spacex.core.initializers.FlipperInitializer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoSet
import timber.log.Timber

@InstallIn(ApplicationComponent::class)
@Module
abstract class  DebugInitializerModule {
    @Binds
    @IntoSet
    abstract fun bindFlipperInitializer(impl: FlipperInitializer): AppInitializer

    companion object {
        @Provides
        @IntoSet
        fun provideDebugTree(): Timber.Tree = Timber.DebugTree()
    }
}