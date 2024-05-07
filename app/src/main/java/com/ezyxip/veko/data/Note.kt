package com.ezyxip.veko.data

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val theme: String,
    val description: String
)
