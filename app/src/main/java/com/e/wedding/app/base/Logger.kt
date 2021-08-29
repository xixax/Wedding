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

    fun v(tag: String, methodName: String, message: String, values: String, vararg args: Any?)

    fun d(tag: String, methodName: String, message: String = EMPTY_STRING)

    fun d(tag: String, methodName: String, message: String, values: String, vararg args: Any?)

    fun i(tag: String, methodName: String, message: String = EMPTY_STRING)

    fun i(tag: String, methodName: String, message: String, values: String, vararg args: Any?)

    fun w(tag: String, methodName: String, message: String = EMPTY_STRING)

    fun w(tag: String, methodName: String, message: String = EMPTY_STRING, throwable: Throwable)

    fun w(tag: String, methodName: String, message: String, values: String, vararg args: Any?)

    fun w(tag: String, methodName: String, message: String = EMPTY_STRING, values: String, throwable: Throwable, vararg args: Any?)

    fun e(tag: String, methodName: String, message: String = EMPTY_STRING)

    fun e(tag: String, methodName: String, message: String = EMPTY_STRING, throwable: Throwable)

    fun e(tag: String, methodName: String, message: String = EMPTY_STRING, values: String = EMPTY_STRING, vararg args: Any?)

    fun e(tag: String, methodName: String, message: String = EMPTY_STRING, values: String = EMPTY_STRING, throwable: Throwable, vararg args: Any?)
}