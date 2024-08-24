package org.example.com.example.demo

import kotlinx.coroutines.*
import org.example.com.example.config.AudioConfig.AUDIO_FILE_PATH
import org.example.com.example.reminder.wateringReminder
import org.testng.annotations.Test
import java.time.Duration
import java.time.LocalDateTime

class ReminderDemo {
    @Test
    fun testWaterReminder() {
        val now = LocalDateTime.now()
        val dateTimeList = listOf(now, now.plusSeconds(4), now.plusSeconds(8), now.plusSeconds(12))

        // 设置停止时间，例如当前时间加2分钟
        val stopTime = now.plusSeconds(16)

        val scope = CoroutineScope(Dispatchers.Default)
        dateTimeList.forEach {
            scope.launch {
                wateringReminder(it, AUDIO_FILE_PATH)
            }
        }

        runBlocking {
            val delayMillis = Duration.between(LocalDateTime.now(), stopTime).toMillis()
            if (delayMillis > 0) {
                delay(delayMillis)
            }
        }
    }
}