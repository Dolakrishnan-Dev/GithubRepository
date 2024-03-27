package com.example.githubuserprofile.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubuserprofile.presentation.state.UIState

@Composable
fun CustomTextField(onClick: ((uiState: UIState) -> Unit)?) {
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent, // Removes default focus indicator
            cursorColor = LocalContentColor.current // Set cursor color
        ),
        placeholder = { Text("Search...") }, // Placeholder text for empty field
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp) // Adjust height as needed
            .padding(horizontal = 16.dp) // Adjust padding as needed
            .background(
                shape = RoundedCornerShape(15.dp), // Set rounded corners for outline
                color = Color.LightGray.copy(alpha = 0.2f) // Set outline color with transparency
            ),
        textStyle = TextStyle(
            fontSize = 16.sp, // Adjust text size as needed
            color = LocalContentColor.current // Set text color
        ),
        leadingIcon = {
            IconButton(onClick = {
                if (searchText.isNotEmpty())
                    onClick?.invoke(UIState.SearchUser(isFromHome = true, userName = searchText))
                keyboardController?.hide()
            }) {
                Icon( // Replace with your desired search icon resource
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        },
        shape = RoundedCornerShape(15.dp),
        keyboardActions = KeyboardActions(onSearch = {
            onClick?.invoke(UIState.SearchUser(isFromHome = true, userName = searchText))
            keyboardController?.hide()
        }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search) // Enable search keyboard action
    )
}