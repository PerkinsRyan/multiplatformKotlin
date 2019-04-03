package com.rperkins.nationalparks

interface MainView {
    fun showParks(parks: List<Park>)
    fun showVisitedCount(count: Int)
}