package org.microg.vending.playapi.exceptions

import java.io.IOException

class AuthException(message: String?) : IOException(message) {
    var code: Int = 0
    var rawResponse: String = ""
}