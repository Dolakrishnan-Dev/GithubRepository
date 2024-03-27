package com.example.githubuserprofile.domain.repository

import com.example.githubuserprofile.data.model.GithubUserDataDto
import com.example.githubuserprofile.data.model.GithubUserRepoListDto
import com.example.githubuserprofile.data.model.GithubUsersListDto
import com.example.githubuserprofile.data.source.ApiResult
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {

    suspend fun getUsersList(): Flow<ApiResult<List<GithubUsersListDto>>>

    suspend fun getUserData(username: String): Flow<ApiResult<GithubUserDataDto>>

    suspend fun getUserRepoList(username: String): Flow<ApiResult<List<GithubUserRepoListDto>>>
}