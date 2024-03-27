package com.example.githubuserprofile.data.source

import com.example.githubuserprofile.data.model.GithubUserDataDto
import com.example.githubuserprofile.data.model.GithubUserRepoListDto
import com.example.githubuserprofile.data.model.GithubUsersListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubUserApi {

    @GET("users")
    suspend fun getUsersList(): Response<List<GithubUsersListDto>>

    @GET("users/{username}/repos")
    suspend fun getUsersRepositories(@Path("username") username: String): Response<List<GithubUserRepoListDto>>

    @GET("users/{username}")
    suspend fun getUserData(@Path("username") username: String): Response<GithubUserDataDto>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}