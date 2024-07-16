package hello.aicampassignment.controller

import com.fasterxml.jackson.annotation.Nulls
import hello.aicampassignment.dto.UrlModelRequest
import hello.aicampassignment.model.Url
import hello.aicampassignment.service.UrlModelService
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.cglib.core.Local
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.Date

@RestController
class UrlModelController(
    val urlModelService: UrlModelService,
    private val UrlModelService: UrlModelService,
) {
    private val logger = LoggerFactory.getLogger(UrlModelController::class.java)

    @PostMapping("/api/short-urls")
    fun createShortUrl(
        @RequestBody
        request: UrlModelRequest,
    ) = urlModelService.createShortUrl(request.original_url)

    @GetMapping("/api/short-urls")
    fun listMyUrls(
        @RequestParam(required = false) createdAfter: LocalDateTime?,
    ) = urlModelService.findByCreatedAtAfter(
            createdAfter ?:LocalDateTime.now().minusMinutes(10)
    )

    @GetMapping("/api/short-urls/{encodedUrl}")
    fun redirect(
        @PathVariable encodedUrl: String,
        response: HttpServletResponse,
    ) {
        logger.error("Request : $encodedUrl")
        val url = UrlModelService.findByEncodedUrl("http://localhost:8080/short-url/" + encodedUrl)
            ?: throw IllegalStateException("Invelid URL")

        response.sendRedirect(url.originalUrl)
    }
}