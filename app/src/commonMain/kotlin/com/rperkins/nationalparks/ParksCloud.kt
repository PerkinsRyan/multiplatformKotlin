package com.rperkins.nationalparks

expect class ParksCloud() {
    fun getParks(onSuccess: (List<Park>) -> Unit)
}