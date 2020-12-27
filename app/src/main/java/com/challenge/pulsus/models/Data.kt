package com.challenge.pulsus.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data  (

    @SerializedName("icon_url")
    var iconUrl : String,
    var url : String,
    var value : String
) : Parcelable