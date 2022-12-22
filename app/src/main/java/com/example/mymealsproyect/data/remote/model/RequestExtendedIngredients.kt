package com.example.mymeal.data.remote.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class RequestExtendedIngredients(
    @SerializedName("id") val id: Int,
    @SerializedName("aisle") val aisle: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("consistency") val consistency: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("nameClean") val nameClean: String?,
    @SerializedName("original") val original: String?,
    @SerializedName("originalName") val originalName: String?,
    @SerializedName("amount") val amount: Double): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(aisle)
        parcel.writeString(image)
        parcel.writeString(consistency)
        parcel.writeString(name)
        parcel.writeString(nameClean)
        parcel.writeString(original)
        parcel.writeString(originalName)
        parcel.writeDouble(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RequestExtendedIngredients> {
        override fun createFromParcel(parcel: Parcel): RequestExtendedIngredients {
            return RequestExtendedIngredients(parcel)
        }

        override fun newArray(size: Int): Array<RequestExtendedIngredients?> {
            return arrayOfNulls(size)
        }
    }
}
