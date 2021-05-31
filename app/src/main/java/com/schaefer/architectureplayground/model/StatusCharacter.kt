package com.schaefer.architectureplayground.model

import com.google.gson.annotations.SerializedName

enum class StatusCharacter {
    @SerializedName("Alive", alternate = ["alive", "ALIVE"])
    ALIVE,

    @SerializedName("Dead", alternate = ["dead", "DEAD"])
    DEAD,

    @SerializedName("unknown", alternate = ["Unknown", "UNKNOWN", ""])
    UNKNOWN,
}