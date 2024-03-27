package com.example.githubuserprofile.domain.model

import com.example.githubuserprofile.common.FilterOptions
import java.util.Date

data class GithubUserDetailData(
    var githubUserData: GithubUserData? = null,
    var githubUserRepoData: List<GithubUserRepoData>? = null,
    var selectedFilterOption: (FilterOptions) -> Unit,
    var isProfileLoading: Boolean = true,
    var isRepoLoading: Boolean = true
)

data class GithubUserData(
    val login: String,
    val id: Long,
    val nodeId: String?,
    val avatarUrl: String?,
    val type: String?,
    val siteAdmin: Boolean?,
    val name: String?,
    val blog: String,
    val location: String?,
    val email: String?,
    val bio: String?,
    val publicRepos: Long?,
    val followers: Long?,
    val createdAt: String?,
    val updatedAt: String?,
)

data class GithubUserRepoData(
    val id: Long,
    val name: String,
    val private: Boolean,
    val description: String?,
    val fork: Boolean,
    val createdAt: String?,
    val updatedAt: String?,
    val updatedAtDate: Date?,
    val size: Long,
    val watchersCount: Long,
    val language: String?,
    val forksCount: Long,
    val forks: Long
)