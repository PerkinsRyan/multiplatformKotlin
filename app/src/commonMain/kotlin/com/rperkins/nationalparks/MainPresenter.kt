package com.rperkins.nationalparks

class MainPresenter(parksCloud: IParksCloud) {
    private lateinit var view: MainView
    private val parkRepository: IParkRepository

    init {
        parkRepository = ParkRepository(parksCloud)
    }

    fun bindView(mainView: MainView) {
        view = mainView

        parkRepository.getAllParks { parks ->
            view.showParks(parks)
            view.showVisitedCount(parks.filter { it.visited }.size)
        }
    }

    fun filterParks(filter: String) = parkRepository.getParks(filter) { parks ->
        view.showParks(parks)
    }
}