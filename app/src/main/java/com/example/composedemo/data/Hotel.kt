package com.example.composedemo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Hotel(
    val id: Long,
    val name: String,
    val location: Location,
    val stars: Int,
    val checkIn: RangeHours,
    val checkOut: RangeHours,
    val contact: Contact,
    val gallery: List<String>,
    val userRating: Double,
    val price: Double,
    val currency: String
) : Parcelable


@Parcelize
@Serializable
data class Location(
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable


@Parcelize
@Serializable
data class RangeHours(
    val from: String,
    val to: String
) : Parcelable


@Parcelize
@Serializable
data class Contact(
    val phoneNumber: String,
    val email: String
) : Parcelable