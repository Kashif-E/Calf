package com.mohamedrejeb.calf.sample.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.calf.ui.sheet.AdaptiveBottomSheet
import com.mohamedrejeb.calf.ui.sheet.rememberAdaptiveSheetState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(
    navigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberAdaptiveSheetState()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.systemBars)
            .windowInsetsPadding(WindowInsets.ime)
    ) {
        IconButton(
            onClick = {
                navigateBack()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBackIosNew,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        Button(
            onClick = {
                openBottomSheet = true
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Show Bottom Sheet")
        }

        if (openBottomSheet) {
            AdaptiveBottomSheet(
                onDismissRequest = {
                    openBottomSheet = false
                },
                adaptiveSheetState = sheetState,
            ) {
                LazyColumn(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {
                    item {
                        Text("Bottom Sheet")
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        openBottomSheet = false
                                    }
                                }
                            }
                        ) {
                            Text("Close")
                        }
                    }

                    items(100) {
                        Text(
                            text = "Item $it",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}