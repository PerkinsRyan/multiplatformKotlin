package sample

class MainPresenter(val parkRepository: ParkRepository) {
    private lateinit var view: MainView

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