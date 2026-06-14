package com.example.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.navigation3.runtime.rememberNavBackStack
import android.navigation3.ui.NavDisplay
import android.navigation3.runtime.entry
import android.navigation3.runtime.entryProvider
import com.example.navigationdemo.screens.*
import com.example.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    // Стек с начальным экраном Home
    val backStack = rememberNavBackStack(HomeScreen)

    // Обработчик переходов на новый экран
    val onNavigation: (NavKey) -> Unit = { key ->
        backStack.add(key)
    }

    // Очистка стека до главного экрана (оставляем только Home)
    val onClearBackStack: () -> Unit = {
        while (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()   // стандартное возвращение назад
            // Альтернатива: очищать всё кроме первого (см. конец лабораторной)
        },
        entryProvider = entryProvider {
            entry<HomeScreen> { Home(onNavigation) }
            entry<WelcomeScreen> { key -> Welcome(onNavigation, key.name) }
            entry<ProfileScreen> { Profile(onClearBackStack) }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NavigationDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}