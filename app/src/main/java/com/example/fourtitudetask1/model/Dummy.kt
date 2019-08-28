package com.example.fourtitudetask1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dummy(
        val title: String,
        val subtitle: String,
        val description: String,
        val imageUrl: String
) : Parcelable



