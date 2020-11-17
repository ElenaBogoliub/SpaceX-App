package com.ebogoliub.spacex.core.extentions

inline val <T> T.exhaustive: T
    get() = this

inline fun Boolean.whenTrue(crossinline f: () -> Unit): Boolean = also { if (this) f() }
inline fun Boolean.whenFalse(crossinline f: () -> Unit): Boolean = also { if (!this) f() }

fun <T> List<T>?.hasAtLeastSize(minSize: Int): Boolean {
    if (this.isNullOrEmpty()) return false
    return this.size >= minSize
}