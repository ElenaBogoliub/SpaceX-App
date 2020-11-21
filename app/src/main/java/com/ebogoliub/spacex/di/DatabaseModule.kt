package com.ebogoliub.spacex.di

import android.content.Context
import androidx.room.Room
import com.ebogoliub.spacex.data.DatabaseTransactionRunner
import com.ebogoliub.spacex.data.SpacexDatabase
import com.ebogoliub.spacex.db.RoomTransactionRunner
import com.ebogoliub.spacex.db.SpacexRoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SpacexRoomDatabase {
        return Room.databaseBuilder(context, SpacexRoomDatabase::class.java, "spacex.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class DatabaseModuleBinds {
    @Binds
    abstract fun bindSpacexDatabase(db: SpacexRoomDatabase): SpacexDatabase

    @Singleton
    @Binds
    abstract fun provideDatabaseTransactionRunner(runner: RoomTransactionRunner): DatabaseTransactionRunner
}

@InstallIn(ApplicationComponent::class)
@Module
object DaoModule {

    @Provides
    fun provideLaunchDao(db: SpacexDatabase) = db.launchDao()

    @Provides
    fun provideRocketsDao(db: SpacexDatabase) = db.rocketsDao()
}