package com.ebogoliub.spacex.inject

import com.ebogoliub.spacex.BuildConfig
import com.ebogoliub.spacex.core.initializers.AppInitializer
import com.ebogoliub.spacex.core.initializers.ThreeTenBpInitializer
import com.ebogoliub.spacex.core.initializers.TimberInitializer
import com.ebogoliub.spacex.core.crash.CrashlyticsInitializer
import com.ebogoliub.spacex.core.crash.CrashlyticsTree
import com.ebogoliub.spacex.core.di.CoroutinesDispatcherProvider
import com.ebogoliub.spacex.core.di.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoSet
import timber.log.Timber

@InstallIn(ApplicationComponent::class)
@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun provideCoroutinesDispatchers(bind: CoroutinesDispatcherProvider): CoroutinesDispatchers

    @Binds
    @IntoSet
    abstract fun provideThreeTenAbpInitializer(bind: ThreeTenBpInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun provideTimberInitializer(bind: TimberInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun bindCrashlyticsTree(crashlyticsTree: CrashlyticsTree): Timber.Tree

    companion object {
        @Provides
        @IntoSet
        fun provideCrashlyticsInitializer(): AppInitializer =
            CrashlyticsInitializer(BuildConfig.DEBUG)
    }
}
