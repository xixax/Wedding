package com.e.wedding.app.base

import android.util.Log
import com.e.wedding.app.model.EMPTY_STRING
import java.text.MessageFormat

class AndroidLogger : Logger {

    override fun v(tag: String, methodName: String, message: String) {
        Log.v(tag, "$methodName${getFinalMessage(message)}")
    }

    override fun d(tag: String, methodName: String, message: String) {
        Log.d(tag, "$methodName${getFinalMessage(message)}")
    }

    override fun i(tag: String, methodName: String, message: String) {
        Log.i(tag, "$methodName${getFinalMessage(message)}")
    }

    override fun w(tag: String, methodName: String, message: String) {
        Log.w(tag, "$methodName${getFinalMessage(message)}")
    }

    override fun e(tag: String, methodName: String, message: String) {
        Log.e(tag, "$methodName${getFinalMessage(message)}")
    }

    private fun getFinalMessage(message: String) = if (message.isEmpty()) EMPTY_STRING else " - $message"
}