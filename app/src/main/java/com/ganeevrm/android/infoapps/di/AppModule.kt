package com.ganeevrm.android.infoapps.di

import android.content.Context
import com.ganeevrm.android.infoapps.data.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideAppRepository(@ApplicationContext context: Context): AppRepository = AppRepository(context)
}