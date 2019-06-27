package com.bsas.androiddevs.manejoerrores.common.exception

open class ControlledException(val reasonStringResource: Int, ex: Exception?) : RuntimeException(ex) {

}

class UIAlertException(reasonStringResource: Int, ex: Exception?) : ControlledException(reasonStringResource, ex) {

}

class UIErrorException(reasonStringResource: Int, ex: Exception?) : ControlledException(reasonStringResource, ex) {

}