package ir.adp.framework.utils

object ExceptionHelper

inline fun avoidException(allowPrint: Boolean = true, t: () -> Unit) {
    try {
        t()
    } catch (e: Exception) {
        if (allowPrint)
            e.printStackTrace()
    }
}

inline fun avoidExceptionError(allowPrint: Boolean, t: () -> Unit, e: () -> Unit) {
    try {
        t()
    } catch (e: Exception) {
        if (allowPrint)
            e.printStackTrace()
        e()
    }
}

inline fun avoidExceptionError(t: () -> Unit, e: (exp: Exception) -> Unit) {
    try {
        t()
    } catch (exp: Exception) {
        exp.printStackTrace()
        e(exp)
    }
}

inline fun <T> avoidExceptionReturn(allowPrint: Boolean, t: () -> T, e: () -> T): T {
    return try {
        t()
    } catch (exp: Exception) {
        if (allowPrint)
            exp.printStackTrace()
        e()
    }
}

inline fun <T> avoidExceptionReturn(t: () -> T, e: () -> T): T {
    return try {
        t()
    } catch (e: Exception) {
        e.printStackTrace()
        e()
    }
}

inline fun <T> avoidExceptionReturnError(t: () -> T, e: (exp: Exception) -> T): T {
    return try {
        t()
    } catch (exp: Exception) {
        exp.printStackTrace()
        e(exp)
    }
}

inline fun avoidExceptionFinal(t: () -> Unit, f: () -> Unit) {
    try {
        t()
    } finally {
        f()
    }
}

inline fun avoidExceptionFinalError(t: () -> Unit, e: (exp: Exception) -> Unit, f: () -> Unit) {
    try {
        t()
    } catch (exp: Exception) {
        exp.printStackTrace()
        e(exp)
    } finally {
        f()
    }
}