package com.example.utils

import be.tarsos.dsp.AudioDispatcher
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory
import be.tarsos.dsp.io.jvm.AudioPlayer
import java.io.File

fun playSound(file: String) {
    try {
        val audioFile = File(file)
        if (!audioFile.exists()) {
            throw IllegalArgumentException("The file $file does not exist.")
        }

        // Create the AudioDispatcher from the file directly
        val dispatcher: AudioDispatcher = AudioDispatcherFactory.fromFile(audioFile, 1024, 0)
        val player = AudioPlayer(dispatcher.format)
        dispatcher.addAudioProcessor(player)
        Thread(dispatcher).start()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
