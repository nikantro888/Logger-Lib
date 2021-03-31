package com.example.log_lib

import android.util.Log

/**
 * @author N.Khidesheli
 * @see logger library is the easiest way to log
 */
object Logger {

    /**
     * @param tag is log tag
     * @param msg is log message
     * @return log message
     */
    fun log(tag: String, msg: String) {
        Log.d(tag, msg)
    }

}