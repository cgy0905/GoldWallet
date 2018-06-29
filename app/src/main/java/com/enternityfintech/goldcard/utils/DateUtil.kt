package com.enternityfintech.goldcard.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 *Created by cool on 2018/6/4
 */
object DateUtil {

    private val data = Date()
    private val YM = SimpleDateFormat("yyyy年MM月", Locale.CHINESE)
    private val YMDHm = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINESE)
    private val YMD = SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE)

    fun formatYMDHm(time: Long): String {
        data.time = time
        return YMDHm.format(data)
    }

    fun formatYMDHm(time: String?): String {
        if (time.isNullOrEmpty()) return ""
        return formatYMDHm(time!!.toLong())
    }

    fun formatYM(time: String?): String {
        if (time.isNullOrEmpty()) return ""
        data.time = time!!.toLong()
        return YM.format(data)
    }

    fun formatYMD(time: String?): String {
        if (time.isNullOrEmpty()) return ""
        data.time = time!!.toLong()
        return YMD.format(data)
    }
}