package com.zmin.baseproject.data.request

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.File

/**
 * @author: ZhangMin
 * @date: 2020/11/20 14:08
 * @version: 1.0
 * @desc:
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class GetBodyRequest(
    var api_key: String,
    var api_secret: String,
    var image_url: String,
    var image_file: File?,
    var image_base64: String
) : Parcelable