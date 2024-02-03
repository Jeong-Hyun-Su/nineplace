package com.side.project.global.config;

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
class ObjectMapperConfig {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Bean
    fun objectMapper(): ObjectMapper {
        val javaTimeModule : JavaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(dateTimeFormatter))
            .addSerializer(LocalDate::class.java, LocalDateSerializer(dateFormatter))

        return jacksonObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setDateFormat(dateFormat)               // Date 변환
            .registerModule(javaTimeModule)          // LocalDateTime 변환
    }
}