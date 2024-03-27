package com.example.githubuserprofile.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserprofile.common.FilterOptions
import com.example.githubuserprofile.common.toGithubUserData
import com.example.githubuserprofile.common.toGithubUserListData
import com.example.githubuserprofile.common.toGithubUserRepoListDto
import com.example.githubuserprofile.data.source.Status
import com.example.githubuserprofile.domain.model.GithubUserDetailData
import com.example.githubuserprofile.domain.model.GithubUserRepoData
import com.example.githubuserprofile.domain.usecase.GithubUserUseCase
import com.example.githubuserprofile.presentation.state.GithubUserState
import com.example.githubuserprofile.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val githubUserUseCase: GithubUserUseCase) :
    ViewModel() {

    private val _githubUsersInfo =
        MutableStateFlow(GithubUserState(onClick = {
            Log.d("githubUserRepoData", "fetchUserData / onUiEvent")
            onUIEvents(uiState = it)
        }))
    var githubUsersInfo = _githubUsersInfo.asStateFlow()

    private val _githubUserDetail =
        MutableStateFlow(GithubUserDetailData(selectedFilterOption = {
            applyFilter(it)
        }))
    var githubUserDetail = _githubUserDetail.asStateFlow()

    private var githubUserRepoData: List<GithubUserRepoData> = emptyList()


     fun fetchGithubUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            githubUserUseCase.getUserUseCase().collectLatest { apiResult ->
                when (apiResult.status) {
                    Status.SUCCESS -> {
                        apiResult.data?.run {
                            val githubUserData = this.toGithubUserListData()
                            _githubUsersInfo.update {
                                it.copy(
                                    isLoading = false,
                                    githubUsers = githubUserData,
                                    error = null
                                )
                            }
                        }
                    }

                    Status.FAILURE -> {
                        Log.e("Error", apiResult.message ?: "")
                        _githubUsersInfo.update {
                            it.copy(
                                isLoading = false,
                                githubUsers = null,
                                error = "Sorry, Something went wrong."
                            )
                        }
                    }
                }
            }
        }
    }

    fun onUIEvents(uiState: UIState) {
        when (uiState) {
            is UIState.FetchUserRepo -> {
                fetchUserRepo(uiState.userName)
            }

            is UIState.SearchUser -> {
                if (uiState.isFromHome && uiState.userName.isEmpty())
                    fetchGithubUsers()
                else {
                    fetchUserData(uiState.isFromHome, uiState.userName)
                }
            }
        }
    }

    private fun fetchUserRepo(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            githubUserUseCase.getUserRepoListUseCase(userName).collectLatest { apiResult ->
                when (apiResult.status) {
                    Status.SUCCESS -> {
                        try {
                            apiResult.data?.run {
                                val githubUserRepoListData = this.toGithubUserRepoListDto()
                                githubUserRepoData = githubUserRepoListData
                                _githubUserDetail.update {
                                    it.copy(
                                        isRepoLoading = false,
                                        githubUserRepoData = githubUserRepoListData
                                    )
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("Error", apiResult.message.orEmpty())
                        }
                    }

                    Status.FAILURE -> {
                        try {
                            _githubUserDetail.update {
                                it.copy(
                                    isRepoLoading = false,
                                    isProfileLoading = false
                                )
                            }
                        } catch (e: Exception) {
                            Log.e(e.message, e.message ?: "")
                        }
                    }
                }
            }
        }
    }

    private fun fetchUserData(fromHome: Boolean, userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            githubUserUseCase.getUserDetailUseCase(userName).collectLatest { apiResult ->
                when (apiResult.status) {
                    Status.SUCCESS -> {
                        apiResult.data?.run {
                            if (fromHome) {
                                val githubUsersListData = this.toGithubUserListData()
                                _githubUsersInfo.update {
                                    it.copy(
                                        isLoading = false,
                                        githubUsers = listOf(githubUsersListData),
                                        error = null
                                    )
                                }
                            } else {

                                val githubUserData = this.toGithubUserData()
                                _githubUserDetail.update {
                                    it.copy(
                                        isProfileLoading = false,
                                        githubUserData = githubUserData
                                    )
                                }
                            }
                        }
                    }

                    Status.FAILURE -> {
                        _githubUserDetail.update {
                            it.copy(
                                isRepoLoading = false,
                                isProfileLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun applyFilter(filterOption: FilterOptions) {
        viewModelScope.launch {
            val githubUserRepoListData = when (filterOption) {
                FilterOptions.NONE -> {
                    githubUserRepoData
                } // Return original data if no filter is selected
                FilterOptions.FORKS -> {
                    githubUserRepoData
                        .sortedByDescending { it.forksCount }
                }

                FilterOptions.WATCHERS -> {
                    githubUserRepoData
                        .sortedByDescending { it.watchersCount }
                }

                FilterOptions.LAST_UPDATED -> {
                    githubUserRepoData
                        .sortedByDescending { it.updatedAtDate }
                }
            }
            Log.d("githubUserRepoData", "applyFilter")
            _githubUserDetail.value = _githubUserDetail.value.copy(
                isRepoLoading = false,
                githubUserRepoData = githubUserRepoListData
            )
        }
    }

}

