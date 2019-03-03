package sample

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.*

internal val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Default

class ParkRepository {

    private val client = HttpClient()

    private val nationalParks = listOf(
        Park("Acadia"),
        Park("American Samoa"),
        Park("Arches", true),
        Park("Badlands"),
        Park("Big Bend"),
        Park("Biscayne", true),
        Park("Black Canyon of the Gunnison", true),
        Park("Byrce Canyon", true),
        Park("Canyonlands", true),
        Park("Capitol Reef", true),
        Park("Carlsbad Caverns"),
        Park("Channel Islands"),
        Park("Congaree", true),
        Park("Crater Lake", true),
        Park("Cuyahoga Valley"),
        Park("Death Valley"),
        Park("Denali"),
        Park("Dry Tortugas"),
        Park("Everglades", true),
        Park("Gates of the Artic"),
        Park("Gateway Arch"),
        Park("Glacier"),
        Park("Glacier Bay"),
        Park("Grand Canyon"),
        Park("Grand Teton", true),
        Park("Great Basin", true),
        Park("Great Sand Dunes", true),
        Park("Great Smoky Mountains", true),
        Park("Gaudalupe Mountains"),
        Park("Haleakala"),
        Park("Hawaii Volcanoes"),
        Park("Hot Springs"),
        Park("Isle Royale"),
        Park("Joshua Tree"),
        Park("Katmai"),
        Park("Kenai Fjords"),
        Park("King's Canyon"),
        Park("Kobuk Valley"),
        Park("Lake Clark"),
        Park("Lassen Volcanic", true),
        Park("Mammoth Cave"),
        Park("Mesa Verde", true),
        Park("Mt. Rainier", true),
        Park("North Cascades", true),
        Park("Olympic", true),
        Park("Petrified Forest"),
        Park("Pinnacles"),
        Park("Redwood", true),
        Park("Rocky Mountain", true),
        Park("Saguaro"),
        Park("Sequoia"),
        Park("Shenandoah"),
        Park("Theodore Roosevelt"),
        Park("Virgin Islands"),
        Park("Voyageurs"),
        Park("Wind Cave"),
        Park("Wrangell-St. Elias"),
        Park("Yellowstone", true),
        Park("Yosemite"),
        Park("Zion", true)
    )

    fun getAllParks(callback: (List<Park>) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: String = client.get {
                    url("https://private-13b17e-ryanperkins1.apiary-mock.com/questions")
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