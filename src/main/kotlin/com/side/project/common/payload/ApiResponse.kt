package com.side.project.common.payload

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ApiResponse<T>(
    var status: HttpStatus,
    var message: String?,
    var data: T?,
    var timeStamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun <T> ok(message: String? = "SUCCESS", data: T? = null) = ApiResponse(status = HttpStatus.OK, message = message, data = data)
        fun <T> created(message: String? = "CREATED", data: T? = null) = ApiResponse(status = HttpStatus.CREATED, message = message, data = data)
        fun error(message: String) = ApiResponse(status = HttpStatus.BAD_REQUEST, message = message, data = null)
    }

    override fun toString() = "ApiResponse(status=$status, message=$message, data=${data.toString()}, timestamp=$timeStamp)"
}