package com.side.project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class SideProjectApplication

fun main(args: Array<String>) {
    runApplication<SideProjectApplication>(*args)
}
