package org.example.com.example.reminder

import com.example.utils.playSound
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDateTime

suspend fun wateringReminder(reminderTime: LocalDateTime, soundFilePath: String) {
    val now = LocalDateTime.now()
    val delayMillis = Duration.between(now, reminderTime).toMillis()

    if (delayMillis > 0) {
        delay(delayMillis)
        playSound(soundFilePath)
    } else {
        println("Reminder time is in the past!")
    }
}
