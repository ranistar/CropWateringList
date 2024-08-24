package com.example.config

import com.example.extensions.parseHours
import com.example.extensions.plus
import org.example.com.example.config.TimeRatioConfig
import org.example.com.example.extensions.toLocalDateTime
import java.time.LocalDateTime
import kotlin.time.Duration

object TimeConfig {
    const val HOLD_TIME = "0:11"
    private const val MATURITY_TIME = "2024/8/24 3:05"
    const val CROP_HOURS = 16

    private const val CROP_MATURITY_RATIO = TimeRatioConfig.CROP_MATURITY_RATIO
    const val MATURITY_WATER_RATIO = TimeRatioConfig.MATURITY_WATER_RATIO
    private const val THIRD_WATER_RATIO = TimeRatioConfig.THIRD_WATER_RATIO


    val maturityTime: LocalDateTime = MATURITY_TIME.toLocalDateTime()

    var totalInfluenceTime: Duration = Duration.ZERO

    val wateringTime by lazy {
        val cropHours = Duration.parseHours(CROP_HOURS)
        cropHours * CROP_MATURITY_RATIO
    }

    val influenceTime by lazy {
        wateringTime * MATURITY_WATER_RATIO
    }
    val initialTime: LocalDateTime by lazy {
        MATURITY_TIME.toLocalDateTime().plusHours(-CROP_HOURS.toLong()).plus(influenceTime)
    }

    private fun fetchWateringList(): MutableList<LocalDateTime> {
        var currentTime = initialTime
        val wateringList = mutableListOf<LocalDateTime>()
        wateringList.add(currentTime)
        repeat(2) {
            currentTime = currentTime.plus(wateringTime)
            wateringList.add(currentTime)
        }
        val thirdWateringTime = influenceTime * THIRD_WATER_RATIO
        totalInfluenceTime = influenceTime * 3 - thirdWateringTime
        wateringList.add(currentTime.plus(thirdWateringTime))
        return wateringList
    }

    val wateringList: MutableList<LocalDateTime> by lazy {
        fetchWateringList()
    }

    init {
        // 确保每次调用刷新maturityTime值
        wateringList
    }
}