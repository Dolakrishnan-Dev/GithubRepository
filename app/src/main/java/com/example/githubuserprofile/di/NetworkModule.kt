package com.example.githubuserprofile.di

import com.example.githubuserprofile.data.repository.GithubUserImpl
import com.example.githubuserprofile.data.source.GithubUserApi
import com.example.githubuserprofile.data.source.GithubUserApi.Companion.BASE_URL
import com.example.githubuserprofile.domain.repository.GithubUserRepository
import com.example.githubuserprofile.domain.usecase.GetUserDetailUseCase
import com.example.githubuserprofile.domain.usecase.GetUserRepoListUseCase
import com.example.githubuserprofile.domain.usecase.GetUsersListUseCase
import com.example.githubuserprofile.domain.usecase.GithubUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): GithubUserApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create()

    @Provides
    @Singleton
    fun provideClient() =
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build()

    @Provides
    @Singleton
    fun provideGithubUserImpl(githubUserApi: GithubUserApi): GithubUserRepository {
        return GithubUserImpl(githubUserApi)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(githubUserRepository: GithubUserRepository): GetUsersListUseCase {
        return GetUsersListUseCase(githubUserRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserDetailUseCase(githubUserRepository: GithubUserRepository): GetUserDetailUseCase {
        return GetUserDetailUseCase(githubUserRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserRepoListUseCase(githubUserRepository: GithubUserRepository): GetUserRepoListUseCase {
        return GetUserRepoListUseCase(githubUserRepository)
    }


    @Provides
    @Singleton
    fun providesGithubUserUseCase(
        getUserUseCase: GetUsersListUseCase,
        getUserDetailUseCase: GetUserDetailUseCase,
        getUserRepoListUseCase: GetUserRepoListUseCase
    ) =
        GithubUserUseCase(
            getUserUseCase = getUserUseCase,
            getUserDetailUseCase = getUserDetailUseCase,
            getUserRepoListUseCase = getUserRepoListUseCase
        )

}