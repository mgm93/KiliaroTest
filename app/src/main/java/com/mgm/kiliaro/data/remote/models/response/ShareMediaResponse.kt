package com.mgm.kiliaro.data.remote.models.response

import com.google.gson.annotations.SerializedName
import java.util.*
import javax.annotation.Nullable

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
data class ShareMediaResponse(
        @SerializedName("id")
        val id: String,

        @SerializedName("user_id")
        val user_id: String,

        @SerializedName("media_type")
        val media_type: String,

        @SerializedName("filename")
        val filename: String,

        @SerializedName("size")
        val size: Long,

        @SerializedName("created_at")
        val created_at: String,

        @Nullable
        @SerializedName("taken_at")
        val taken_at: Objects,

        @Nullable
        @SerializedName("guessed_taken_at")
        val guessed_taken_at: String,

        @SerializedName("md5sum")
        val md5sum: String,

        @SerializedName("content_type")
        val content_type: String,

        @Nullable
        @SerializedName("video")
        val video: String,

        @SerializedName("thumbnail_url")
        val thumbnail_url: String,

        @SerializedName("download_url")
        val download_url: String,

        @SerializedName("resx")
        val resx: Int,

        @SerializedName("resy")
        val resy: Int

)

