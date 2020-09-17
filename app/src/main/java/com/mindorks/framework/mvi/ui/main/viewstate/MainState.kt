package com.mindorks.framework.mvi.ui.main.viewstate

import com.mindorks.framework.mvi.data.model.Api_Response


sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: Api_Response) : MainState()
    data class Error(val error: String?) : MainState()

}