package com.mindorks.framework.mvi.ui.main

import com.mindorks.framework.mvi.data.model.Data

interface Communicator {

    fun passUserData(list: List<Data>)
}