package com.example.githubuserprofile.data.model

import com.google.gson.annotations.SerializedName

data class GithubUserDataDto(
    val login: String,
    val id: Long,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val type: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    val name: String?,
    val blog: String,
    val location: String?,
    val email: String?,
    val bio: String?,
    @SerializedName("public_repos")
    val publicRepos: Long,
    val followers: Long,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)

