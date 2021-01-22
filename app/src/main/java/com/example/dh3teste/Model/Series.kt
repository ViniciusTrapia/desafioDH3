package com.example.dh3teste.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    val name: String?,
    val resourceURI: String?
): Parcelable