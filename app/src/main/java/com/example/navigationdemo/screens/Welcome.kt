package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationdemo.ProfileScreen

@Composable
fun Welcome(onNavigation: (NavKey) -> Unit, name: String = "") {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Welcome $name",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.size(30.dp))
            Button(onClick = { onNavigation(ProfileScreen) }) {
                Text("Set up your Profile")
            }
        }
    }
}