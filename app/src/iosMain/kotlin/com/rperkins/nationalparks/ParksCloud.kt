package com.rperkins.nationalparks

actual class ParksCloud actual constructor() {
    actual fun getParks(onSuccess: (List<Park>) -> Unit) {
        onSuccess(listOf())
    }
}