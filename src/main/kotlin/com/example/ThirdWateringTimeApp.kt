package com.example
import com.example.config.TimeConfig.MATURITY_WATER_RATIO
import com.example.config.TimeConfig.earliestTime
import com.example.config.TimeConfig.effectTime
import com.example.config.TimeConfig.maturityTime
import com.example.extensions.gapTo
import com.example.extensions.plus
import com.example.extensions.toDHHMMString
import com.example.extensions.toFormattedString
import com.example.utils.playSound
import kotlinx.coroutines.*
import org.example.com.example.config.AudioConfig.AUDIO_FILE_PATH
import java.sql.SQLOutput
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes
// 12:12
//
fun thirdWateringTimeApp() {
    runBlocking {
        val initialNow = LocalDateTime.now()
        var stopTimeA: LocalDateTime = initialNow
        var stopTimeB: LocalDateTime = maturityTime
        if (stopTimeA > stopTimeB) {
            return@runBlocking
        }
        println(effectTime.toDHHMMString())
        stopTimeB = stopTimeB.plus(-effectTime)
        while (stopTimeA < stopTimeB) {
            stopTimeA = stopTimeA.plus(1.minutes)
            stopTimeB = stopTimeB.plus((-1).minutes * MATURITY_WATER_RATIO)
        }
        
        var stopTime = stopTimeA
        // 如果计算后的停止时间和成熟时间的差值小于32，则更新为当前时间 + 差值
        println(initialNow gapTo maturityTime)
        println(earliestTime)
        if (initialNow gapTo maturityTime < earliestTime) {
            stopTime = maturityTime
        }


        val job = launch(Dispatchers.Default) {
            while (isActive) {
                val now = LocalDateTime.now()
                println(stopTime.toFormattedString())
                if (now.isAfter(stopTime)) {
                    playSound(AUDIO_FILE_PATH)
                    break
                }
                delay(1000)
            }
        }
        job.join()
    }
}
fun main() {
    thirdWateringTimeApp()
}