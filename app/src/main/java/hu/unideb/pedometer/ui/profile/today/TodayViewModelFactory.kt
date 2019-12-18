package hu.unideb.pedometer.ui.profile.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.unideb.pedometer.database.HistoryDAO

class TodayViewModellFactory(
    private val dataSource: HistoryDAO
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodayViewModel::class.java)) {
            return TodayViewModel(
                dataSource
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}