package com.example.dh3teste.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    val extension: String?,
    var path: String?
): Parcelable