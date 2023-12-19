package com.example.webservice2.dataclasses

import com.google.gson.annotations.SerializedName

data class ImageCatsResponse (

    @SerializedName("breeds" ) var breeds : ArrayList<Breeds> = arrayListOf(),
    @SerializedName("height" ) var height : Int?              = null,
    @SerializedName("id"     ) var id     : String?           = null,
    @SerializedName("url"    ) var url    : String?           = null,
    @SerializedName("width"  ) var width  : Int?              = null

)
