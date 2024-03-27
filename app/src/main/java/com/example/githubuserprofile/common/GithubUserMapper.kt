package com.example.githubuserprofile.common

import com.example.githubuserprofile.data.model.GithubUserDataDto
import com.example.githubuserprofile.data.model.GithubUserRepoListDto
import com.example.githubuserprofile.data.model.GithubUsersListDto
import com.example.githubuserprofile.domain.model.GithubUserData
import com.example.githubuserprofile.domain.model.GithubUserRepoData
import com.example.githubuserprofile.domain.model.GithubUsersListData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun List<GithubUsersListDto>.toGithubUserListData() = this.map { it.toGithubUserListData() }

fun GithubUsersListDto.toGithubUserListData() =
    GithubUsersListData(
        login.replaceFirstChar { it.uppercaseChar() },
        id,
        nodeId,
        avatarUrl,
        type,
        siteAdmin
    )

fun GithubUserDataDto.toGithubUserListData() =
    GithubUsersListData(
        login = login.replaceFirstChar { it.uppercaseChar() },
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        type = type,
        siteAdmin = siteAdmin
    )

fun GithubUserDataDto.toGithubUserData() = GithubUserData(
    login.replaceFirstChar { it.uppercaseChar() },
    id,
    nodeId,
    avatarUrl,
    type,
    siteAdmin,
    name,
    blog,
    location,
    email,
    bio,
    publicRepos,
    followers,
    createdAt.convertDateFormat(),
    updatedAt.convertDateFormat()
)


fun List<GithubUserRepoListDto>.toGithubUserRepoListDto() =
    this.map { it.toGithubUserRepoListDto() }


fun GithubUserRepoListDto.toGithubUserRepoListDto() =
    GithubUserRepoData(
        id,
        name.replaceFirstChar { it.uppercaseChar() },
        private,
        description,
        fork,
        createdAt?.convertDateFormat(),
        updatedAt?.convertDateFormat(),
        updatedAt?.parseDateString(),
        size,
        watchersCount,
        language,
        forksCount,
        forks
    )

fun String.parseDateString(): Date? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    return inputFormat.parse(this)
}

fun String.convertDateFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val date = inputFormat.parse(this)
    return date?.let { outputFormat.format(it) }.orEmpty()
}