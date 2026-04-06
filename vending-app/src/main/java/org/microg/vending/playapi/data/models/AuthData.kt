package org.microg.vending.playapi.data.models

import org.microg.vending.playapi.data.providers.DeviceInfoProvider
import java.util.Locale

data class AuthData(
    val email: String,
    val aasToken: String
) {
    var authToken: String = ""
    var gsfId: String = ""
    var tokenDispenserUrl: String = ""
    var ac2dmToken: String = ""
    var androidCheckInToken: String = ""
    var deviceCheckInConsistencyToken: String = ""
    var deviceConfigToken: String = ""
    var experimentsConfigToken: String = ""
    var gcmToken: String = ""
    var oAuthLoginToken: String = ""
    var dfeCookie: String = ""
    var locale: Locale = Locale.getDefault()
    var deviceInfoProvider: DeviceInfoProvider? = null
}