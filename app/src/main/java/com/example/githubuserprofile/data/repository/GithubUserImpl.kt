package com.example.githubuserprofile.data.repository

import com.example.githubuserprofile.data.model.GithubUserDataDto
import com.example.githubuserprofile.data.model.GithubUserRepoListDto
import com.example.githubuserprofile.data.model.GithubUsersListDto
import com.example.githubuserprofile.data.source.ApiResult
import com.example.githubuserprofile.data.source.GithubUserApi
import com.example.githubuserprofile.data.source.safeApiCall
import com.example.githubuserprofile.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubUserImpl @Inject constructor(private val githubUserApi: GithubUserApi) :
    GithubUserRepository {

    override suspend fun getUsersList(): Flow<ApiResult<List<GithubUsersListDto>>> =
        safeApiCall {
            githubUserApi.getUsersList()
        }

    override suspend fun getUserData(username: String): Flow<ApiResult<GithubUserDataDto>> =
        safeApiCall {
            githubUserApi.getUserData(username)
        }

    override suspend fun getUserRepoList(username: String): Flow<ApiResult<List<GithubUserRepoListDto>>> =
        safeApiCall {
            githubUserApi.getUsersRepositories(username)
        }

}