package com.leaf.data.model

import com.google.gson.annotations.SerializedName

/**
 * ErrorResponse
 * Data class used to model the api error messages.
 * @param code [Int] error code.
 * @param message [String] represents error message.
 */
data class Error(
    @SerializedName("code")
    val code : Int,
    @SerializedName("message")
    val message : String
)
