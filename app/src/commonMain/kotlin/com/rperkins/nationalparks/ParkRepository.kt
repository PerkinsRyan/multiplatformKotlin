package com.rperkins.nationalparks

import kotlinx.coroutines.*

internal expect val ApplicationDispatcher: CoroutineDispatcher

class ParkRepository(private val parksCloud: IParksCloud): IParkRepository {
    private var nationalParks = emptyList<Park>()

    override fun getAllParks(callback: (List<Park>) -> Unit) {
        parksCloud.getParks { parks ->
            nationalParks = parks
            callback(nationalParks)
        }
    }

    override fun getParks(filter: String, callback: (List<Park>) -> Unit) = callback(
        nationalParks.filter { it.name.contains(filter, true) }
    )
}