package com.example.demo

import com.example.extensions.parseTime
import com.example.extensions.plus
import com.example.extensions.toFormattedString
import org.testng.annotations.Test
import java.time.LocalDateTime
import kotlin.time.Duration

class ExFuncDemo {
    @Test
    fun testPlus() {
        val myDuration = Duration.parseTime("1:30")
        // 测试代码
        println( LocalDateTime.now().plus(myDuration).toFormattedString())
    }
}