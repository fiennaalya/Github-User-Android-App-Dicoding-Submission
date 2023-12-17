package com.example.submissiondicoding1.datauser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var uname: String? = null,
    var name: String? = null,
    var foto : String? = null,
    var kantor: String? = null,
    var asal : String? = null,
    var repository: String? = null,
    var followers: String? = null,
    var following: String? = null,
    var isFavorite : String? = null
):Parcelable


