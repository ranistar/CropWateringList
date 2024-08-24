package com.example.extensions

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

// 扩展函数
fun Duration.Companion.parseTime(time: String): Duration {
    // 定义正则表达式以匹配“时:分”格式
    val regex = Regex("""^(\d{1,2}):(\d{2})$""")
    val matchResult = regex.matchEntire(time)

    if (matchResult != null) {
        // 提取小时和分钟
        val hours = matchResult.groups[1]?.value?.toInt() ?: 0
        val minutes = matchResult.groups[2]?.value?.toInt() ?: 0

        // 确保分钟在0到59之间
        if (minutes !in 0..59) {
            throw IllegalArgumentException("Minutes must be between 0 and 59.")
        }

        // 使用 toDuration 构造 Duration
        return (hours.toLong() * 60 + minutes).toDuration(DurationUnit.MINUTES)
    }

    // 如果不匹配，则抛出异常
    throw IllegalArgumentException("Invalid time format. Use 'hh:mm'.")
}

fun Duration.Companion.parseHours(hours: Int): Duration {
    return (hours.toLong()).toDuration(DurationUnit.HOURS)
}

fun Duration.toDHHMMString(): String {
    // 获取总天数
    val days = this.inWholeDays
    // 获取剩余小时数
    val hours = this.inWholeHours % 24
    // 获取剩余分钟数
    val minutes = (this.inWholeMinutes % 60)

    // 以 d HH:mm 格式返回字符串
    return String.format("%d %02d:%02d", days, hours, minutes)
}
