package com.example

import com.example.config.TimeConfig.CROP_HOURS
import com.example.config.TimeConfig.influenceTime
import com.example.config.TimeConfig.initialTime
import com.example.config.TimeConfig.maturityTime
import com.example.config.TimeConfig.totalInfluenceTime
import com.example.config.TimeConfig.wateringList
import com.example.extensions.plus
import com.example.extensions.toDHHMMString
import com.example.extensions.toFormattedString
import kotlinx.coroutines.*
import org.example.com.example.config.AudioConfig.AUDIO_FILE_PATH
import org.example.com.example.reminder.wateringReminder
import java.time.Duration
import java.time.LocalDateTime

fun main() {
    // 获取当前时间
    val now = LocalDateTime.now()
    // 设置停止时间，例如当前时间加2分钟
    var stopTime = now.plusSeconds(12)

    // 创建一个 CoroutineScope
    val scope = CoroutineScope(Dispatchers.Default)

    // 打印信息
    println("""
        作物用时：${CROP_HOURS}H
        开始时间: ${initialTime.toFormattedString()}
        成熟时间：${maturityTime.plus(-totalInfluenceTime).toFormattedString()}
        
        -----------------------------------
        ${
        wateringList.joinToString("<-||->") {
            // 启动浇水提醒任务
            scope.launch {
                wateringReminder(it, AUDIO_FILE_PATH)
            }
            val index = wateringList.indexOf(it)
            if (wateringList.size - 1 == index) stopTime = it.plusSeconds(10)
            "第${index + 1}次浇水时间: ${it.toFormattedString()}"
            
        }
    }
        -----------------------------------
        浇水减少用时: ${(totalInfluenceTime + influenceTime).toDHHMMString()}
        stopTime: ${stopTime.toFormattedString()}
    """.trimIndent())

    // 使用 runBlocking 阻塞主线程直到指定的 LocalDateTime
    runBlocking {
        val delayMillis = Duration.between(LocalDateTime.now(), stopTime).toMillis()
        if (delayMillis > 0) {
            delay(delayMillis)
        }
    }
}