package com.ebogoliub.spacex.core.extentions

fun <T> List<T>?.hasAtLeastSize(minSize: Int): Boolean {
    if (this.isNullOrEmpty()) return false
    return this.size >= minSize
}