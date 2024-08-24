package org.example.com.example.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

// 扩展方法
fun String.toLocalDateTime(): LocalDateTime {
    // 定义日期时间格式
    val formatter = DateTimeFormatter.ofPattern("yyyy/M/d H:mm")
    // 解析字符串并返回 LocalDateTime
    return LocalDateTime.parse(this, formatter)
}
fun String.toDuration(): Duration {
    val parts = this.split(":")
    require(parts.size == 2) { "The input string must be in the format HH:MM" }

    val hours = parts[0].toIntOrNull() ?: throw IllegalArgumentException("Invalid hours value")
    val minutes = parts[1].toIntOrNull() ?: throw IllegalArgumentException("Invalid minutes value")

    return hours.hours + minutes.minutes
}
