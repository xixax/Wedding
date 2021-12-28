package com.e.wedding.app.base

interface Tagged {
    val TAG: String get() = javaClass.simpleName

    val logger: Logger get() = Logger.get()
}