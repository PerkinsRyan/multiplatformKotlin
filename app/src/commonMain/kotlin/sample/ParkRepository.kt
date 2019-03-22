package sample

import kotlinx.coroutines.*

internal expect val ApplicationDispatcher: CoroutineDispatcher

class ParkRepository {
    private val api = ParksApi("https://private-13b17e-ryanperkins1.apiary-mock.com/")
    private var nationalParks = emptyList<Park>()

    fun getAllParks(callback: (List<Park>) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                nationalParks = api.getAllParks()

                callback(nationalParks)
            }
        }
    }

    fun getParks(filter: String, callback: (List<Park>) -> Unit) = callback(
        nationalParks.filter { it.name.contains(filter, true) }
    )
}