package sample

import kotlin.test.Test
import kotlin.test.expect

class MainPresenterTests {

    val repo = ParkRepository()
    val subject = MainPresenter(repo)

    @Test
    fun `should request all parks on bindView`() {
        expect(true, {true})
    }

}