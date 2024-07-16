package hello.aicampassignment.service

import hello.aicampassignment.dto.MqttDto
import hello.aicampassignment.model.Mqtt
import org.springframework.data.jpa.repository.JpaRepository

interface MqttRepository : JpaRepository<Mqtt, Long> {
    fun findByClientId(findByClientId: String): List<Mqtt>
}