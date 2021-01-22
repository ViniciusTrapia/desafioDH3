package com.example.dh3teste.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stories(
    val available: Int?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: Int?
): Parcelable