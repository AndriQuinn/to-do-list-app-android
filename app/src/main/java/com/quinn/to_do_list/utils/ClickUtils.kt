package com.quinn.to_do_list.utils

fun (() -> Unit).singleClick(cooldownMillis: Long = 500L): () -> Unit {
    var lastClickTime = 0L
    return {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > cooldownMillis) {
            lastClickTime = currentTime
            this()
        }
    }
}