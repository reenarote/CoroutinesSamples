package com.reeamu.suspendcancellablecoroutinessample.data.dto

data class User(val name: String, val degree: String) {
    override fun toString(): String {
        return "Name: $name & Degree: $degree"
    }
}
