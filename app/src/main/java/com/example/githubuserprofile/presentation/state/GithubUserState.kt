package com.example.githubuserprofile.presentation.state

import com.example.githubuserprofile.domain.model.GithubUsersListData

data class GithubUserState(
    var isLoading: Boolean = true,
    var githubUsers: List<GithubUsersListData>? = null,
    var error: String? = null,
    var onClick: ((uiState: UIState) -> Unit)? = null
)