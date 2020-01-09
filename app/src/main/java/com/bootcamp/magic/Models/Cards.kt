package com.bootcamp.magic.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cards(val cards:ArrayList<Card>) : Parcelable