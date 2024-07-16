package hello.aicampassignment.service

import hello.aicampassignment.model.Url
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class UrlModelService (
    val urlModelRepository: UrlModelRepository
) {
    fun save(url: Url): Url = urlModelRepository.save(url)

    fun findAll(): List<Url> = urlModelRepository.findAll()

    @Transactional
    fun findByEncodedUrl(EncodedUrl: String): Url? {
        var result: Url? = urlModelRepository.findByEncodedUrl(EncodedUrl)
        result?.clickCount = result?.clickCount?.plus(1)!!
        return result
    }

    fun createShortUrl(originalUrl: String): Url {
        val existingUrl = findByEncodedUrl(originalUrl)
        if (existingUrl != null) {
            return existingUrl
        }

        val encodedUrl = "http://localhost:8080/short-url/" + originalUrl.hashCode().toString(36)

        val url = Url(
            originalUrl = originalUrl,
            encodedUrl = encodedUrl
        )
        return urlModelRepository.save(url)
    }

    fun findByCreatedAtAfter(createdAfter: LocalDateTime): List<Url> =
        urlModelRepository.findByCreatedAtAfter(createdAfter)
}

