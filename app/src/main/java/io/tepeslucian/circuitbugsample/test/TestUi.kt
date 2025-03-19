package io.tepeslucian.circuitbugsample.test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

class TestUiFactory: Ui.Factory {
    override fun create(
        screen: Screen,
        context: CircuitContext
    ): Ui<*>? {
        return if (screen is TestScreen) {
            ui<TestState> { state, modifier -> TestUi(state, modifier) }
        } else {
            null
        }
    }
}

@Composable
fun TestUi(
    state: TestState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable { state.eventSink(TestUiEvent.Click) }
            .background(state.screen.color)
            .fillMaxSize(),
    ) {
        if (!state.screen.isRoot) {
            IconButton(
                onClick = {
                    state.eventSink(TestUiEvent.Back)
                },
                modifier = Modifier
                    .background(Color.White),
            ) {
                Text(
                    text = "Back",
                )
            }
        }
        Text(
            text = state.desc,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}