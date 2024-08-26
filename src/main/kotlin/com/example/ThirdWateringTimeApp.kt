package com.example
import com.example.config.TimeConfig.holdTime
import com.example.config.TimeConfig.maturityTime
import com.example.extensions.plus
import com.example.utils.playSound
import kotlinx.coroutines.*
import org.example.com.example.config.AudioConfig.AUDIO_FILE_PATH
import java.time.LocalDateTime

fun main() {
    runBlocking {
        val job = launch(Dispatchers.Default) {
            while (isActive) {
                val now = LocalDateTime.now()
                if (now.isAfter(maturityTime.plus(-holdTime))) {
                    playSound(AUDIO_FILE_PATH)
                    break
                }
                delay(1000)
            }
        }
        job.join()
    }
}