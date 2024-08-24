package org.example.com.example.demo

import com.example.config.TimeConfig
import org.example.com.example.config.TimeRatioConfig.THIRD_WATER_RATIO
import org.testng.annotations.Test

class ConfigDemo {
    @Test
    fun testWateringTime() {
        println(TimeConfig.wateringTime)
    }

    @Test
    fun testInfluenceTime() {
        println(TimeConfig.influenceTime * THIRD_WATER_RATIO)
    }

    @Test
    fun testWateringList() {
        println(TimeConfig.wateringList)
    }
}