package hello.aicampassignment.service

import hello.aicampassignment.model.Url
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface UrlModelRepository : JpaRepository<Url, Long> {
    fun findByEncodedUrl(findByEncodedUrl: String): Url?

    fun findByCreatedAtAfter(createdAfter: LocalDateTime): Url?
}