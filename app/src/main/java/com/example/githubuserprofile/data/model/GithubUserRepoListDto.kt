package com.example.githubuserprofile.data.model

import com.google.gson.annotations.SerializedName

data class GithubUserRepoListDto(
    val id: Long,
    val name: String,
    val private: Boolean,
    val description: String? = null,
    val fork: Boolean,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    val size: Long,
    @SerializedName("watchers_count")
    val watchersCount: Long,
    val language: String? = null,
    @SerializedName("forks_count")
    val forksCount: Long,
    val forks: Long,
)
