package com.caldremch.android.entry.compiler.base;


import javax.annotation.processing.Messager;
import javax.tools.Diagnostic.Kind;

/**
 * @author Caldremch
 * @date 2020-08-27 13:56
 * @email caldremch@163.com
 * @describe
 **/
public final class Logger {

    private final Messager msg;
    private final String TAG = "";

    public final void info(CharSequence info) {
        this.msg.printMessage(Kind.NOTE, TAG + info + "'\n'");
    }

    public final void error(CharSequence error) {
        this.msg.printMessage(Kind.ERROR, TAG + error + "'\n'");
    }

    public final void error(Throwable error) {
        if (error != null) {
            Messager var10000 = this.msg;
            Kind var10001 = Kind.ERROR;
            StackTraceElement[] var10003 = error.getStackTrace();
            var10000.printMessage(var10001, (CharSequence) this.formatStackTrace(var10003));
        }

    }

    public final void warning(CharSequence warning) {
        this.msg.printMessage(Kind.WARNING, TAG + warning + "'\n'");
    }

    private final String formatStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] var5 = stackTrace;
        int var6 = stackTrace.length;

        for (int var4 = 0; var4 < var6; ++var4) {
            StackTraceElement element = var5[var4];
            sb.append("    at ").append(element.toString());
            sb.append("\n");
        }

        String var10000 = sb.toString();
        return var10000;
    }


    public final Messager getMsg() {
        return this.msg;
    }

    public Logger(Messager msg) {
        this.msg = msg;
    }
}
