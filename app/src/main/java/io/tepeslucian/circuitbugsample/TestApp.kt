@file:OptIn(ExperimentalCircuitApi::class)

package io.tepeslucian.circuitbugsample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.ExperimentalCircuitApi
import com.slack.circuitx.gesturenavigation.GestureNavigationDecorationFactory
import io.tepeslucian.circuitbugsample.test.ROOT_SCREENS
import io.tepeslucian.circuitbugsample.test.TestPresenterFactory
import io.tepeslucian.circuitbugsample.test.TestScreen
import io.tepeslucian.circuitbugsample.test.TestUiFactory

@Composable
fun TestApp() {
    val backstack = rememberSaveableBackStack(ROOT_SCREENS[0])
    val navigator = rememberCircuitNavigator(backstack)
    val circuit = remember {
        Circuit.Builder()
            .addPresenterFactory(TestPresenterFactory())
            .addUiFactory(TestUiFactory())
            .addAnimatedScreenTransform(TestScreen::class, TestAnimTransform)
            .build()
    }

    CircuitCompositionLocals(circuit) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar {
                    val rootScreen by remember(backstack) {
                        derivedStateOf {
                            backstack.last().screen
                        }
                    }
                    ROOT_SCREENS.forEach { tabItem ->
                        NavigationBarItem(
                            selected = tabItem == rootScreen,
                            label = {
                                Text("Root ${tabItem.value}")
                            },
                            onClick = {
                                navigator.resetRoot(tabItem)
                            },
                            icon = {}
                        )
                    }

                }
            },
        ) { innerPadding ->
            NavigableCircuitContent(
                navigator = navigator,
                backStack = backstack,
                modifier = Modifier
                    .padding(innerPadding),
                decoratorFactory = remember(navigator) {
                    GestureNavigationDecorationFactory(onBackInvoked = navigator::pop)
                },
            )
        }
    }
}