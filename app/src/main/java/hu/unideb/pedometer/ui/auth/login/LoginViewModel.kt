package hu.unideb.pedometer.ui.auth.login

import androidx.lifecycle.ViewModel
import hu.unideb.pedometer.database.UserDAO

class LoginViewModel(val database: UserDAO) : ViewModel() {
    var users = database.getAllUser()

    fun updateUserList() {
        users = database.getAllUser()
    }


}
