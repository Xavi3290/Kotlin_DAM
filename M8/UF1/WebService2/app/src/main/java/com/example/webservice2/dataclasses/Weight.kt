package com.example.webservice2.dataclasses

import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("imperial" ) var imperial : String? = null,
    @SerializedName("metric"   ) var metric   : String? = null
)
