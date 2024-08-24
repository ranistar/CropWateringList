package com.example

import com.example.config.TimeConfig.HOLD_TIME
import com.example.extensions.toFormattedString
import com.example.extensions.plus
import org.example.com.example.extensions.toDuration
import java.time.LocalDateTime

val now: LocalDateTime = LocalDateTime.now()
fun main() {
    println("干涸剩余时间: $HOLD_TIME")
    println("当前时间：" + now.toFormattedString())
    println("下次浇水时间: " + now.plus(HOLD_TIME.toDuration()).toFormattedString())

}