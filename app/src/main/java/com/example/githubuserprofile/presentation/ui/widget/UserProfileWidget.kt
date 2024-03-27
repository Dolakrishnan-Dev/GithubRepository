package com.example.githubuserprofile.presentation.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.githubuserprofile.domain.model.GithubUsersListData

@Composable
fun UserProfileWidget(githubUserData: GithubUsersListData, navigate: (userName: String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { navigate.invoke(githubUserData.login) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = githubUserData.avatarUrl,
            contentDescription = "UserImage",
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = githubUserData.login,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = "ID: ${githubUserData.id}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfile() {
    UserProfileWidget(
        githubUserData = GithubUsersListData(
            "Dola",
            1,
            "",
            "https://avatars.githubusercontent.com/u/1?v=4",
            "User",
            false
        ),
        navigate = { }
    )
}