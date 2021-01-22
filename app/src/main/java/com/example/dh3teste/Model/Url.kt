package com.example.dh3teste.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Url(
    val type: String?,
    val url: String?
): Parcelable