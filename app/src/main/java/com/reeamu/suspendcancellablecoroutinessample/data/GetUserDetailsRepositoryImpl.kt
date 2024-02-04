package com.reeamu.suspendcancellablecoroutinessample.data

import com.reeamu.suspendcancellablecoroutinessample.data.dto.User

class GetUserDetailsRepositoryImpl : GetUserDetailsRepository {
    override fun invoke(callback: UserCallback) {
        callback.userDetails(User("Alice", "Phd"))
    }
}