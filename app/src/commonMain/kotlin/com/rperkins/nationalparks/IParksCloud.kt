package com.rperkins.nationalparks

interface IParksCloud {
    fun getParks(onSuccess: (List<Park>) -> Unit)
}