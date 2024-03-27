package com.example.githubuserprofile.domain.usecase

import com.example.githubuserprofile.domain.repository.GithubUserRepository

class GetUserDetailUseCase(private val githubUserRepository: GithubUserRepository) {

    suspend operator fun invoke(userName: String) = githubUserRepository.getUserData(userName)

}