package com.rperkins.nationalparks

interface IParkRepository {
    fun getAllParks(callback: (List<Park>) -> Unit)
    fun getParks(filter: String, callback: (List<Park>) -> Unit)
}