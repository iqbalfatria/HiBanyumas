package com.tubes.hibanyumas.tempatbersejarah

import android.os.Parcel
import android.os.Parcelable

data class MyData(
    val name: String?,
    val description: String?,
    val photo: String?,
    val lat: Double,
    val lang: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(photo)
        parcel.writeDouble(lat)
        parcel.writeDouble(lang)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<MyData> {
        override fun createFromParcel(parcel: Parcel): MyData {
            return MyData(parcel)
        }
        override fun newArray(size: Int): Array<MyData?> {
            return arrayOfNulls(size)
        }
    }
}