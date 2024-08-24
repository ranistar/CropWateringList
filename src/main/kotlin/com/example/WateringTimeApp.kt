package com.example

import com.example.config.TimeConfig.HOLD_TIME
import com.example.config.TimeConfig.MATURITY_WATER_RATIO
import com.example.config.TimeConfig.wateringTime
import com.example.extensions.plus
import com.example.extensions.toDHHMMString
import com.example.extensions.toFormattedString
import kotlinx.coroutines.*
import org.example.com.example.config.AudioConfig.AUDIO_FILE_PATH
import org.example.com.example.extensions.toDuration
import org.example.com.example.reminder.wateringReminder
import java.time.Duration
import java.time.LocalDateTime

fun main() {
    val now: LocalDateTime = LocalDateTime.now()
    println("干涸剩余时间: $HOLD_TIME")
    println("当前时间：" + now.toFormattedString())
    val holdTime = HOLD_TIME.toDuration()
    val effectTime = (wateringTime - holdTime) * MATURITY_WATER_RATIO
    println("当前影响时间" + effectTime.toDHHMMString())
    val wateringTime = now.plus(holdTime)
    println("下次浇水时间: ${wateringTime.toFormattedString()}")
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        wateringReminder(wateringTime, AUDIO_FILE_PATH)
    }
    val stopTime = wateringTime.plusSeconds(5)

    runBlocking {
        val delayMillis = Duration.between(LocalDateTime.now(), stopTime).toMillis()
        if (delayMillis > 0) {
            delay(delayMillis)
        }
    }

}