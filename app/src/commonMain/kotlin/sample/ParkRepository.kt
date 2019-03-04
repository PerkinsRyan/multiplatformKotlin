package sample

import kotlinx.coroutines.*

internal val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Default

class ParkRepository {

    private val api = ParksApi("http://private-13b17e-ryanperkins1.apiary-mock.com/")
    private var nationalParks = emptyList<Park>()

    fun getAllParks(callback: (List<Park>) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                if (nationalParks.isEmpty()) {
                    nationalParks = api.getAllParks()
                }

                launch(Dispatchers.Main) {
                    callback(nationalParks)
                }
            }
        }
    }

    fun getParks(filter: String, callback: (List<Park>) -> Unit) = callback(
        nationalParks.filter { it.name.contains(filter, true) }
    )
}