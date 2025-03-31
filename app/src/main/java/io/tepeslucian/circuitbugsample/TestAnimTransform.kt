@file:OptIn(ExperimentalCircuitApi::class)

package io.tepeslucian.circuitbugsample

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.slack.circuit.foundation.animation.AnimatedNavEvent
import com.slack.circuit.foundation.animation.AnimatedNavState
import com.slack.circuit.foundation.animation.AnimatedScreenTransform
import com.slack.circuit.runtime.ExperimentalCircuitApi

object TestAnimTransform : AnimatedScreenTransform {

    override fun AnimatedContentTransitionScope<AnimatedNavState>.exitTransition(
        animatedNavEvent: AnimatedNavEvent
    ): ExitTransition? {
        return fadeOut(tween(5000))
    }

    override fun AnimatedContentTransitionScope<AnimatedNavState>.enterTransition(
        animatedNavEvent: AnimatedNavEvent
    ): EnterTransition? {
        return fadeIn(tween(5000))
    }
}