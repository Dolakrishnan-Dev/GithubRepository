package com.example.githubuserprofile.presentation.state

sealed class UIState {

    data class SearchUser(val isFromHome: Boolean, val userName: String) : UIState()

    data class FetchUserRepo(val userName: String) : UIState()
}
