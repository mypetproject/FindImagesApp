package com.example.findimagesapp.DI

import com.example.findimagesapp.domain.ImagesInteractorImpl
import com.example.findimagesapp.domain.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository() = RepositoryImpl(ImagesInteractorImpl())
}