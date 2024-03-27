package com.example.githubuserprofile.domain.usecase

import com.example.githubuserprofile.domain.repository.GithubUserRepository

class GetUsersListUseCase(private val githubUserRepository: GithubUserRepository) {

    suspend operator fun invoke() = githubUserRepository.getUsersList()

}