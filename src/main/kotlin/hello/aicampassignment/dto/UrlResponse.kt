package hello.aicampassignment.dto

import java.time.LocalDateTime

data class UrlResponse (
    val original_url: String,

    val encoded_url: String,

    val click_count: Int,

    val created_at: LocalDateTime,
)