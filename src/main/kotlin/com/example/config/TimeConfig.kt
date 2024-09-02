package com.example.config

import com.example.extensions.parseHours
import com.example.extensions.plus
import org.example.com.example.config.TimeRatioConfig
import org.example.com.example.extensions.toDuration
import org.example.com.example.extensions.toLocalDateTime
import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

object TimeConfig {
    const val HOLD_TIME = "3:12"
    private const val MATURITY_TIME = "2024/9/2 13:07"
    const val CROP_HOURS = 16

    private const val CROP_MATURITY_RATIO = TimeRatioConfig.CROP_MATURITY_RATIO
    const val MATURITY_WATER_RATIO = TimeRatioConfig.MATURITY_WATER_RATIO
    private const val THIRD_WATER_RATIO = TimeRatioConfig.THIRD_WATER_RATIO
    private val cropHour = CROP_HOURS.hours

    val holdTime = HOLD_TIME.toDuration()

    val maturityTime: LocalDateTime = MATURITY_TIME.toLocalDateTime()

    var totalInfluenceTime: Duration = Duration.ZERO

    val wateringTime by lazy {
        cropHour * CROP_MATURITY_RATIO
    }

    val influenceTime by lazy {
        wateringTime * MATURITY_WATER_RATIO
    }
    val earliestTime = (CROP_HOURS * 2).minutes
    val effectTime  = ((wateringTime - holdTime) * MATURITY_WATER_RATIO).takeIf { it >= 8.minutes } ?: Duration.ZERO
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