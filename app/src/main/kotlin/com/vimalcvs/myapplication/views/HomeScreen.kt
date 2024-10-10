package com.vimalcvs.myapplication.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vimalcvs.myapplication.data.ModelLive
import com.vimalcvs.myapplication.utils.BottomNavigation
import com.vimalcvs.myapplication.utils.ErrorScreen
import com.vimalcvs.myapplication.utils.LoadingScreen
import com.vimalcvs.myapplication.viewmodel.PostViewModel
import com.vimalcvs.myapplication.viewmodel.UiState

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: PostViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar() },
        bottomBar = { BottomNavigation(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainList(
                uiState = uiState,
                onRetryClick = { viewModel.getPosts() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(modifier = Modifier
        .fillMaxWidth()

        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.Menu, contentDescription = "More"
                )
            }
        },
        title = { Text(text = "Hi, Vimal") },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
            }
        })
}


@Composable
fun MainList(
    uiState: UiState,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        when (uiState) {
            is UiState.Loading -> LoadingScreen()
            is UiState.Error -> ErrorScreen(message = uiState.message, onRetryClick = onRetryClick)
            is UiState.SuccessPost -> MainSectionList(model = uiState.posts)
        }
    }
}

@Composable
fun MainSectionList(model: ModelLive) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = model.title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = model.subtitle,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Score:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Team A: ${model.score.teamA}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Team B: ${model.score.teamB}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}
