package com.example.githubuserprofile.domain.model

data class GithubUsersListData(
    val login: String,
    val id: Long,
    val nodeId: String,
    val avatarUrl: String? = null,
    val type: String,
    val siteAdmin: Boolean,
)
