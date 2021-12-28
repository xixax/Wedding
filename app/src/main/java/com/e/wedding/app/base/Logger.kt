package com.e.wedding.app.base

import com.e.wedding.app.model.EMPTY_STRING

interface Logger {
    companion object {
        private lateinit var logger: Logger

        fun injectLogger(logger: Logger) {
            this.logger = logger
        }

        fun get(): Logger {
            return logger
        }
    }

    fun v(tag: String, methodName: String, message: String = EMPTY_STRING)

    fun d(tag: String, methodName: String, message: String = EMPTY_STRING)

    fun i(tag: String, methodName: String, message: String = EMPTY_STRING)

    fun w(tag: String, methodName: String, message: String = EMPTY_STRING)

    fun e(tag: String, methodName: String, message: String = EMPTY_STRING)
}