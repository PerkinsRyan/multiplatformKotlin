package com.rperkins.nationalparks

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Park(
    val name: String = "",
    @Optional val visited: Boolean = false
)