package com.bsas.androiddevs.manejoerrores.common.exception

class UIAlertException(val reasonStringResource: Int, ex: Exception?) : RuntimeException(ex) {

}

class UIErrorException(val reasonStringResource: Int, ex: Exception?) : RuntimeException(ex) {

}