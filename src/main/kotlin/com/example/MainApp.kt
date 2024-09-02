package com.example

import java.util.*
import kotlin.system.exitProcess

fun main() {
    val scanner = Scanner(System.`in`)

    // 菜单选项
    val options = mapOf(
        "1" to "完美浇水列表计时",
        "2" to "浇水时间计时",
        "3" to "最后浇水时间计时",
        "4" to "退出"
    )

    // 显示菜单
    fun printMenu() {
        println("Menu:")
        options.forEach { (key, value) -> println("$key: $value") }
    }

    // 处理用户输入
    fun handleInput(input: String) {
        when (input) {
            "1", "完美浇水列表计时" -> perfectWateringApp()
            "2", "浇水时间计时" -> wateringTimeApp()
            "3", "最后浇水时间计时" -> thirdWateringTimeApp()
            "quit", "q" -> {
                println("Exiting...")
                exitProcess(0)
            }
            else -> println("Invalid option")
        }
    }

    // 主循环
    while (true) {
        printMenu()
        print("Enter your choice: ")
        val userInput = scanner.nextLine().trim()
        handleInput(userInput)
    }
}