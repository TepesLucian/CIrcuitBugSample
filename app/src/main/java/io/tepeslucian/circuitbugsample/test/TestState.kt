package io.tepeslucian.circuitbugsample.test

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class TestState(
    val screen: TestScreen,
    val eventSink: (TestUiEvent) -> Unit,
): CircuitUiState {
    val desc: String
        get() = "${if (screen.isRoot) "Root" else "Screen"} Value ${screen.value}"
}

sealed interface TestUiEvent: CircuitUiEvent {
    data object Click: TestUiEvent
    data object Back: TestUiEvent
}