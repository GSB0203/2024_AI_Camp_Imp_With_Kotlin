package hello.aicampassignment.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "urls")
data class Url (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val originalUrl: String,

    @Column(nullable = false, unique = true)
    var encodedUrl: String,

    var clickCount: Int = 0,

    val createdAt: LocalDateTime = LocalDateTime.now()
)