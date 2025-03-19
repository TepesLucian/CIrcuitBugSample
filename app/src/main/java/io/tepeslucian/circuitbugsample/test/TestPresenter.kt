package io.tepeslucian.circuitbugsample.test

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen

class TestPresenterFactory: Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return if (screen is TestScreen) {
            TestPresenter(screen, navigator)
        } else {
            null
        }
    }
}

class TestPresenter(
    val screen: TestScreen,
    val navigator: Navigator,
): Presenter<TestState> {
    @Composable
    override fun present(): TestState {
        return TestState(screen) { event ->
            when (event) {
                is TestUiEvent.Back -> {
                    navigator.pop()
                }
                is TestUiEvent.Click -> {
                    navigator.goTo(TestScreen(if (screen.isRoot) 4 else screen.value + 1))
                }
            }
        }
    }
}