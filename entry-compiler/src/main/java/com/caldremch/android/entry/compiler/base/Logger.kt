package com.caldremch.android.entry.compiler.base

import javax.annotation.processing.Messager
import javax.tools.Diagnostic


/**
 *
 * @author Caldremch
 *
 * @date 2020-08-18 13:24
 *
 * @email caldremch@163.com
 *
 * @describe 参考了 ARouter
 *
 **/
class Logger(val msg: Messager) {


    /**
     * Print info log.
     */
    fun info(info: CharSequence) {
        msg.printMessage(Diagnostic.Kind.NOTE, "$info \n")
    }

    fun error(error: CharSequence) {
        msg.printMessage(
            Diagnostic.Kind.ERROR,
            "$error \n"
        )
    }

    fun error(error: Throwable?) {
        if (null != error) {
            msg.printMessage(
                Diagnostic.Kind.ERROR,
                formatStackTrace(
                    error.stackTrace
                )
            )
        }
    }

    fun warning(warning: CharSequence) {
        msg.printMessage(Diagnostic.Kind.WARNING, "$warning \n")
    }

    private fun formatStackTrace(stackTrace: Array<StackTraceElement>): String {
        val sb = StringBuilder()
        for (element in stackTrace) {
            sb.append("    at ").append(element.toString())
            sb.append("\n")
        }
        return sb.toString()
    }

}