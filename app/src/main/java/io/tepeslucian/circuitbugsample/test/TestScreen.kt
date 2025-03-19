package io.tepeslucian.circuitbugsample.test

import androidx.compose.ui.graphics.Color
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestScreen(val value: Int): Screen {

    val isRoot: Boolean
        get() = value <= 3

    val color: Color
        get() = when (value) {
            3 -> Color.Blue
            2 -> Color.Green
            1 -> Color.Red
            4 -> Color.DarkGray
            5 -> Color.Magenta
            6 -> Color.Cyan
            7 -> Color.Yellow
            8 -> Color.LightGray
            else -> Color.White
        }
}

val ROOT_SCREENS = listOf(TestScreen(1), TestScreen(2), TestScreen(3))