package com.esaudev.flowworkshop

sealed class UiStates {
    object StandBy: UiStates()

    object NetworkError: UiStates()
}
