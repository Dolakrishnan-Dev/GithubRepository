package com.example.githubuserprofile.domain.usecase

data class GithubUserUseCase(
    val getUserUseCase: GetUsersListUseCase,
    val getUserDetailUseCase: GetUserDetailUseCase,
    val getUserRepoListUseCase: GetUserRepoListUseCase
)
