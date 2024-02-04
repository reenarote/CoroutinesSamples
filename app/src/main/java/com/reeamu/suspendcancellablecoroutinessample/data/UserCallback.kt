package com.reeamu.suspendcancellablecoroutinessample.data

import com.reeamu.suspendcancellablecoroutinessample.data.dto.User

interface UserCallback {
    fun userDetails(user: User)
}