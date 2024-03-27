package com.example.githubuserprofile.domain.usecase

import com.example.githubuserprofile.data.model.GithubUserRepoListDto
import com.example.githubuserprofile.data.source.ApiResult
import com.example.githubuserprofile.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow

class GetUserRepoListUseCase(private val githubUserRepository: GithubUserRepository) {

    suspend operator fun invoke(userName: String): Flow<ApiResult<List<GithubUserRepoListDto>>> {
        return githubUserRepository.getUserRepoList(userName)
    }

}