package com.mgm.kiliaro.generals

import android.annotation.SuppressLint
import java.lang.String.*

/**
* Created by Majid-Golmoradi on 3/15/2022.
* Email: golmoradi.majid@gmail.com
*/

private const val MEGABYTE = 1024L * 1024F

@SuppressLint("DefaultLocale")
fun bytesToMeg(bytes: Long): String {
    return format("%.2f MB", bytes / MEGABYTE)
}