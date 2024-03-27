package com.example.githubuserprofile.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubuserprofile.presentation.state.GithubUserState
import com.example.githubuserprofile.presentation.ui.widget.CustomTextField
import com.example.githubuserprofile.presentation.ui.widget.HomeShimmer
import com.example.githubuserprofile.presentation.ui.widget.UserProfileWidget


@Composable
fun MainScreen(githubUserState: GithubUserState,navigate: (userName: String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {

        CustomTextField(githubUserState.onClick)

        githubUserState.githubUsers?.apply {
            LazyColumn {
                items(this@apply, key = { it.id }) {
                    UserProfileWidget(it,navigate)
                }
            }
        } ?: LazyColumn {
            items(10) {
                HomeShimmer()
            }
        }
    }

}