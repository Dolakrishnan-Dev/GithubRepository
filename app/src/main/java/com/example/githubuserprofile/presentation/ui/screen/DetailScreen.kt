package com.example.githubuserprofile.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.githubuserprofile.common.FilterOptions
import com.example.githubuserprofile.domain.model.GithubUserData
import com.example.githubuserprofile.domain.model.GithubUserDetailData
import com.example.githubuserprofile.domain.model.GithubUserRepoData
import com.example.githubuserprofile.presentation.ui.widget.CustomTextViewCompose

@Composable
fun DetailScreen(githubUserDetailData: GithubUserDetailData) {

    Column(modifier = Modifier.fillMaxSize()) {

        githubUserDetailData.githubUserData?.let { ProfileCard(it) }

        FilterCard(githubUserDetailData.selectedFilterOption)

        githubUserDetailData.githubUserRepoData?.let { githubUserRepoData ->
            LazyColumn {
                items(githubUserRepoData) { repo ->
                    RepositoryItem(repo)
                }
            }
        }

    }

}

@Composable
fun FilterCard(selectedFilterOption: (FilterOptions) -> Unit) {

    var filterForks by remember { mutableStateOf(false) }

    var filterWatchers by remember { mutableStateOf(false) }

    var filterLastUpdated by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        FilterChip(
            onClick = {
                filterForks = filterForks.not()
                filterWatchers = false
                filterLastUpdated = false
                val filerOption = if (filterForks) FilterOptions.FORKS else FilterOptions.NONE
                selectedFilterOption(filerOption)
            },
            label = {
                Text("Forks")
            },
            selected = filterForks,
            leadingIcon = if (filterForks) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )

        Spacer(modifier = Modifier.width(6.dp))

        FilterChip(
            onClick = {
                filterWatchers = filterWatchers.not()
                filterForks = false
                filterLastUpdated = false
                val filerOption = if (filterWatchers) FilterOptions.WATCHERS else FilterOptions.NONE
                selectedFilterOption(filerOption)
            },
            label = {
                Text("Watchers")
            },
            selected = filterWatchers,
            leadingIcon = if (filterWatchers) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )

        Spacer(modifier = Modifier.width(6.dp))

        FilterChip(
            onClick = {
                filterLastUpdated = filterLastUpdated.not()
                filterWatchers = false
                filterForks = false
                val filerOption = if (filterLastUpdated) FilterOptions.LAST_UPDATED else FilterOptions.NONE
                selectedFilterOption(filerOption)
            },
            label = {
                Text("Last Updated")
            },
            selected = filterLastUpdated,
            leadingIcon = if (filterLastUpdated) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )
    }
}

@Composable
fun ProfileCard(githubUserData: GithubUserData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Profile Image
                AsyncImage(
                    model = githubUserData.avatarUrl,
                    contentDescription = "UserImage",
                    modifier = Modifier
                        .size(width = 70.dp, height = 90.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )

                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "Name: ${githubUserData.login}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    githubUserData.email?.let {
                        Text(
                            text = "Email: ${githubUserData.email}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Text(
                        text = "Followers: ${githubUserData.followers}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    githubUserData.bio?.let {
                        Text(
                            text = "Bio: ${githubUserData.bio}",
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2
                        )
                    }
                    githubUserData.publicRepos?.let {
                        Text(
                            text = "Repos: ${githubUserData.publicRepos}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun RepositoryItem(repo: GithubUserRepoData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            repo.run {
                CustomTextViewCompose(
                    label = "Repo: ",
                    content = name,
                    style = MaterialTheme.typography.titleMedium
                )

                description?.let {
                    val modifier = if (description.length > 90) {
                        Modifier
                            .height(65.dp)
                            .verticalScroll(rememberScrollState())
                    } else Modifier
                    CustomTextViewCompose(
                        label = "Description: ",
                        content = description,
                        modifier = modifier
                    )
                }

                language?.let {
                    CustomTextViewCompose(label = "Language: ", content = language)
                }

                CustomTextViewCompose(label = "Forks: ", content = forksCount.toString())

                CustomTextViewCompose(label = "Watchers: ", content = watchersCount.toString())

                updatedAt?.let {
                    CustomTextViewCompose(label = "Updated At: ", content = updatedAt)
                }

            }


        }
    }
}
