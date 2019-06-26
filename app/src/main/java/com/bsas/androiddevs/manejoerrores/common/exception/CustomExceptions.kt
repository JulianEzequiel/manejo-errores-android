package com.bsas.androiddevs.manejoerrores.common.exception

import java.lang.RuntimeException

class UIAlertException(message: String, ex: Exception?) : RuntimeException(message, ex) {

    val reason : String = message

}

class UIErrorException(message: String, ex: Exception?) : RuntimeException(message, ex) {

    val reason : String = message

}