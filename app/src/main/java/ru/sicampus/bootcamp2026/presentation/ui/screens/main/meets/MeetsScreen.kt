package ru.sicampus.bootcamp2026.presentation.ui.screens.main.meets

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material.*
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import ru.sicampus.bootcamp2026.domain.entities.meet.Meet
import ru.sicampus.bootcamp2026.domain.entities.meet.MeetTimeSlot
import java.time.Instant
import java.time.LocalDate

enum class MeetsTab {
    CREATED,
    INVITED
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun MeetsScreen(
    viewModel: MeetsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedTab by rememberSaveable { mutableStateOf(MeetsTab.CREATED) }
    val snackbarHostState = remember { SnackbarHostState() }
    val testmeet = Meet(
        id = 3,
        organizerId = 103,
        title = "Backend Sync",
        description = null,
        timeSlot = MeetTimeSlot(
            id = 3,
            date = LocalDate.of(2026, 2, 23),
            startHour = 11,
            endHour = 13
        ),
        membersIds = listOf(103, 108),
            invitedIds = listOf(109),
            createdAt = Instant.now()
    )

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { e ->
            when(e) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = e.message,
                    )
                }
            }
        }
    }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(modifier = Modifier.padding(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(data.visuals.message)
                }
            }
        }
    },
        floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.addMeet(testmeet) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 24.dp)
        ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Мои встречи",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))

                MeetsTabs(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                MeetsColumn(viewModel, state, selectedTab)

            }
    }
}



@Composable
fun MeetsColumn(
    viewModel: MeetsViewModel,
    state: MeetsState,
    selectedTab: MeetsTab
) {
    PullToRefreshBox(
        isRefreshing = state is MeetsState.Loading,
        onRefresh = { viewModel.getData() }
    ) {
        when (val s = state) {
            is MeetsState.Loading -> {
                BoxCenter {
                    CircularProgressIndicator()
                }
            }
            is MeetsState.Error -> {
                BoxCenter {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = s.reason.ifBlank { "Ошибка загрузки" },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = viewModel::getData) {
                            Text("Повторить")
                        }
                    }
                }
            }
            is MeetsState.Content -> {
                val currentItems = when (selectedTab) {
                    MeetsTab.CREATED -> s.createdMeets
                    MeetsTab.INVITED -> s.invitedMeets
                }

                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(
                        items = currentItems,
                        key = { it.toString() } // если есть нормальный id — лучше подставить его
                    ) { item ->
                        MeetCard(
                            value = item.toString(),
                            onClick = { /* TODO */ }
                        )
                    }
                }
            }
        }
    }

}


@Composable
private fun MeetsTabs(
    selectedTab: MeetsTab,
    onTabSelected: (MeetsTab) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTab.ordinal,
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLowest),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        contentColor = MaterialTheme.colorScheme.onSurface,
        indicator = { },
        divider = { }
    ) {
        Tab(
            selected = selectedTab == MeetsTab.CREATED,
            onClick = { onTabSelected(MeetsTab.CREATED) },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    if (selectedTab == MeetsTab.CREATED)
                        MaterialTheme.colorScheme.secondaryContainer
                    else
                        MaterialTheme.colorScheme.surfaceContainerLowest
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (selectedTab == MeetsTab.CREATED) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text("Созданные", style = MaterialTheme.typography.labelLarge)
            }
        }

        Tab(
            selected = selectedTab == MeetsTab.INVITED,
            onClick = { onTabSelected(MeetsTab.INVITED) },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    if (selectedTab == MeetsTab.INVITED)
                        MaterialTheme.colorScheme.secondaryContainer
                    else
                        MaterialTheme.colorScheme.surfaceContainerLowest
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (selectedTab == MeetsTab.INVITED) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text("Приглашения", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
private fun MeetCard(
    value: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // 1:1 структура как раньше: заголовок + 2 строки
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BoxCenter(content: @Composable () -> Unit) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { content() }
}