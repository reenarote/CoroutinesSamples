package com.reeamu.suspendcancellablecoroutinessample.data


interface GetUserDetailsRepository {
     fun invoke(callback: UserCallback)
}