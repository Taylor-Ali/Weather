package com.leaf.domain.model

data class PlaceDomainModel(
    val id: Long? = null,
    val name: String,
    val latitude: Double,
    val longitude: Double
)