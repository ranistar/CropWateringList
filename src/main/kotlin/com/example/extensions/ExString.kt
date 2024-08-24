package org.example.com.example.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// 扩展方法
fun String.toLocalDateTime(): LocalDateTime {
    // 定义日期时间格式
    val formatter = DateTimeFormatter.ofPattern("yyyy/M/d H:mm")
    // 解析字符串并返回 LocalDateTime
    return LocalDateTime.parse(this, formatter)
}