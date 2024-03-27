package com.example.githubuserprofile.data.model

import com.google.gson.annotations.SerializedName

data class GithubUsersListDto(
    val login: String,
    val id: Long,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val type: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
)
