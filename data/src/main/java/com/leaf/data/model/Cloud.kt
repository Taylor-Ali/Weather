package com.leaf.data.model

import com.google.gson.annotations.SerializedName


/**
 * Cloud
 * Data class used to model the cloud cover data from the weather response.
 * @param all [Double] represents the cloud coverage.
 */
data class Cloud(
    @SerializedName("all")
    val all : Double
)