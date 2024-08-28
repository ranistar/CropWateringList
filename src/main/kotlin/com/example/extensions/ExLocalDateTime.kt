package com.example.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.time.Duration
import kotlin.time.toDuration
import kotlin.time.DurationUnit
fun LocalDateTime.plus(duration: Duration): LocalDateTime {
    // 将 Duration 转换为秒
    val seconds = duration.inWholeSeconds
    // 计算新的 LocalDateTime
    return this.plusSeconds(seconds)
}

fun LocalDateTime.toFormattedString(): String {

    val formatter = DateTimeFormatter.ofPattern("yyyy/M/d H:mm")
    return this.format(formatter)
}
infix fun LocalDateTime.gapTo(other: LocalDateTime): Duration {
    // 计算两个 LocalDateTime 之间的差值，单位为纳秒
    val nanosDifference = ChronoUnit.NANOS.between(this, other)
    // 转换为 Kotlin 的 Duration
    return nanosDifference.toDuration(DurationUnit.NANOSECONDS)
}

