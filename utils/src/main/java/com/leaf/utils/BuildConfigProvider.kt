package com.leaf.utils


object BuildConfigProvider {
    var apiUrl: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var apiKey: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
}