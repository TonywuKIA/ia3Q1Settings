@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.ia3q1settings


import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@Composable
fun SettingsScreen(
    onBack: () -> Unit = {},
    onAbout: () -> Unit = {}
) {
    // states
    var notifications by remember { mutableStateOf(true) }
    var haptics by remember { mutableStateOf(false) }
    var analytics by remember { mutableStateOf(true) }
    var autoUpdate by remember { mutableStateOf(false) }
    var volume by remember { mutableFloatStateOf(0.6f) }

    // snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // show snackbar quickly
                        // NOTE: launch in composition scope
                    }) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        // Launch scope for snackbar
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp) // padding requirement
        ) {
            // AssistChip (Material 3 component)
            AssistChip(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Tip: Tap a row to toggle quickly.")
                    }
                },
                label = { Text("Quick tip") },
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) }
            )

            Spacer(Modifier.height(12.dp))

            // Card (Material 3 component)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(
                        text = "Preferences",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(8.dp))

                    // Row item 1 (ListItem + Switch)
                    SettingRow(
                        icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
                        title = "Notifications",
                        subtitle = "Receive updates and reminders",
                        onRowClick = { notifications = !notifications },
                        right = {
                            Switch(
                                checked = notifications,
                                onCheckedChange = { notifications = it }
                            )
                        }
                    )

                    HorizontalDivider(Modifier.padding(vertical = 6.dp)) // Divider component

                    // Row item 2 (Checkbox)
                    SettingRow(
                        title = "Haptic feedback",
                        subtitle = "Vibrate on important actions",
                        onRowClick = { haptics = !haptics },
                        right = {
                            Checkbox(
                                checked = haptics,
                                onCheckedChange = { haptics = it }
                            )
                        }
                    )

                    HorizontalDivider(Modifier.padding(vertical = 6.dp))

                    // Row item 3 (Switch)
                    SettingRow(
                        title = "Share analytics",
                        subtitle = "Help improve the app by sending usage data",
                        onRowClick = { analytics = !analytics },
                        right = {
                            Switch(
                                checked = analytics,
                                onCheckedChange = { analytics = it }
                            )
                        }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Another Card section with Slider + Button
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(
                        text = "Audio & Updates",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(8.dp))

                    // Slider row
                    SettingRow(
                        title = "Volume",
                        subtitle = "Adjust in-app sound level",
                        onRowClick = {},
                        right = {
                            Slider(
                                value = volume,
                                onValueChange = { volume = it },
                                modifier = Modifier.sizeIn(minWidth = 140.dp, maxWidth = 200.dp)
                            )
                        }
                    )

                    HorizontalDivider(Modifier.padding(vertical = 6.dp))

                    // Auto-update switch
                    SettingRow(
                        title = "Auto-update",
                        subtitle = "Download updates automatically on Wi-Fi",
                        onRowClick = { autoUpdate = !autoUpdate },
                        right = {
                            Switch(
                                checked = autoUpdate,
                                onCheckedChange = { autoUpdate = it }
                            )
                        }
                    )

                    HorizontalDivider(Modifier.padding(vertical = 6.dp))

                    // Button row
                    SettingRow(
                        icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) },
                        title = "Theme",
                        subtitle = "Switch between light and dark mode",
                        onRowClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Theme picker not implemented in Q1.")
                            }
                        },
                        right = {
                            Button(
                                onClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Theme picker not implemented in Q1.")
                                    }
                                },
                                modifier = Modifier.heightIn(min = 40.dp)
                            ) { Text("Change") }
                        }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // ListItem example (Material 3 component) + clickable + border + clip + background
            ListItem(
                headlineContent = { Text("About") },
                supportingContent = { Text("Version, licenses, and acknowledgements") },
                leadingContent = { Icon(Icons.Default.Info, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { onAbout() }
                    .padding(vertical = 6.dp)
            )
        }
    }
}

/** Reusable row: left Column(weight) + right control */
@Composable
private fun SettingRow(
    title: String,
    subtitle: String,
    onRowClick: () -> Unit,
    right: @Composable () -> Unit,
    icon: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onRowClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) { icon() }
            Spacer(Modifier.width(10.dp))
        }

        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(2.dp))
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Spacer(Modifier.width(12.dp))
        Box { right() }
    }
}